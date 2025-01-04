package controllers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import models.Masa;
import models.Siparis;

public class Mutfak {
    private List<Siparis> yemekler;
    private Map<Masa, String> adisyonNotlari; // Masa ve adisyon notunu tutan yapı


    public Mutfak() {
        yemekler = new ArrayList<>();
        new Timer();
        adisyonNotlari = new HashMap<>();

    }

    // Yemek ekleme metodu
    public void yemekEkle(ArrayList<Siparis> yemekler) {
        this.yemekler.addAll(yemekler);
    }

    // Masanın adisyon notunu ekle
    public void adisyonNotuEkle(Masa masa) {
        adisyonNotlari.put(masa, masa.getAdisyonNotu());
    }

    // Adisyon notunu görüntüle
    public String getAdisyonNotu(Masa masa) {
        return adisyonNotlari.getOrDefault(masa, "Adisyon notu yok");
    }

    // Yemeklerin hazırlanmasını simüle et
    public void yemekHazirla() {
        for (Siparis siparis : yemekler) {
            int hazırlanmaSüresi = siparis.getHazırlanmasüresi();  // Süre saniye cinsinden
            TimerTask timerTask = new TimerTask() {
                private int kalanSüre = hazırlanmaSüresi;
    
                @Override
                public void run() {
                    if (kalanSüre > 0) {
                        kalanSüre -= 1;  // Her saniye kalan süreyi azalt
                        siparis.setKalanHazırlanmaSüresi(kalanSüre);  // Kalan süreyi saniye cinsinden ayarla
                    } else {
                        System.out.println(siparis.getUrun() + " hazırlandı!");
                        siparis.setHazırlandı(true);
                        this.cancel();  // TimerTask'ı iptal et
                    }
                }
            };
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(timerTask, 0, 1000);  // Her saniye çalıştır
        }
    }

    // Yemeklerin listesini döndür
    public List<Siparis> getYemekler() {
        return yemekler;
    }
}