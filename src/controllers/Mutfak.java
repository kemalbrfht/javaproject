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
            // Eğer yemek daha önce hazırlanmışsa veya Timer zaten çalışıyorsa, işlem yapma
            if (siparis.isHazırlandı() || siparis.getTimer() != null) {
                continue;
            }
    
            // Yemek hazırlanma süresi için bir TimerTask oluştur
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    int kalanSüre = siparis.getKalanHazırlanmaSüresi();
                    if (kalanSüre > 0) {
                        kalanSüre -= 1;  // Her saniye kalan süreyi azalt
                        siparis.setKalanHazırlanmaSüresi(kalanSüre);  // Kalan süreyi güncelle
                    } else {
                        System.out.println(siparis.getUrun() + " hazırlandı!");
                        siparis.setHazırlandı(true); // Yemek hazırlandı olarak işaretle
                        siparis.getTimer().cancel(); // Timer'ı iptal et
                        siparis.setTimer(null); // Timer referansını sıfırla
                    }
                }
            };
    
            // Yeni bir Timer oluştur ve siparişe ata
            Timer timer = new Timer();
            siparis.setTimer(timer);
            timer.scheduleAtFixedRate(timerTask, 0, 1000); // Her saniyede bir çalıştır
        }
    }
    
    
    // Yemeklerin listesini döndür
    public List<Siparis> getYemekler() {
        return yemekler;
    }
}