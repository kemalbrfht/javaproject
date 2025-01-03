import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Mutfak {
    private List<Siparis> yemekler;
    private Timer timer;

    public Mutfak() {
        yemekler = new ArrayList<>();
        timer = new Timer();
    }

    // Yemek ekleme metodu
    public void yemekEkle(Siparis siparis) {
        yemekler.add(siparis);
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
