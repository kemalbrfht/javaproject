
import java.util.ArrayList;
import java.util.List;

public class Masa {
    private int masaNo;
    private boolean dolu;
    private List<Siparis> siparisler;
    private Mutfak mutfak;
    private String adisyonNotu;

    public String getAdisyonNotu() {
        return adisyonNotu;
    }

    public void setAdisyonNotu(String adisyonNotu) {
        this.adisyonNotu = adisyonNotu;
    }

    public Mutfak getMutfak() {
        return mutfak;
    }

    public void setMutfak(Mutfak mutfak) {
        this.mutfak = mutfak;
    }

    public Masa(int masaNo) {
        this.masaNo = masaNo;
        this.dolu = false;
        this.siparisler = new ArrayList<>();
        this.adisyonNotu="";
    }

    public int getMasaNo() {
        return masaNo;
    }

    public boolean isDolu() {
        return dolu;
    }

    public void setDolu(boolean dolu) {
        this.dolu = dolu;
    }

    public List<Siparis> getSiparisler() {
        return siparisler;
    }

    public void masaAc() {
        if (!dolu) {
            dolu = true;
            System.out.println("Masa " + masaNo + " açıldı.");
        } else {
            System.out.println("Masa zaten dolu!");
        }
    }

    public void masaKapat() {
        dolu = false;
        siparisler.clear();
        System.out.println("Masa " + masaNo + " kapatıldı.");
    }

    public double hesaplaHesap() {
        double toplam = 0;
        for (Siparis siparis : siparisler) {
            toplam += siparis.getFiyat();
        }
        return toplam;
    }
}
