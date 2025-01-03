import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Mutfak {
    private List<Siparis> yemekler;
    private Timer timer;
    private Map<Masa, String> adisyonNotlari; // Masa ve adisyon notunu tutan yapı


    public Mutfak() {
        yemekler = new ArrayList<>();
        timer = new Timer();
        adisyonNotlari = new HashMap<>();

    }


    // Yemek ekleme metodu
    public void yemekEkle(Siparis siparis) {
        yemekler.add(siparis);
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
            // Her yemek için hazırlanma süresi kadar zamanlayıcı başlatıyoruz
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    
                    System.out.println(siparis.getUrun() + " hazırlandı!");
                }
            }, (long) (siparis.getFiyat() * 1000));  // Süre saniye cinsinden
        }
    }

    // Yemeklerin listesini döndür
    public List<Siparis> getYemekler() {
        return yemekler;
    }
}