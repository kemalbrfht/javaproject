
public class Siparis {
    private String kategori;
    private String urun;
    private double fiyat;

    public Siparis(String kategori, String urun, double fiyat) {
        this.kategori = kategori;
        this.urun = urun;
        this.fiyat = fiyat;
    }

    public String getKategori() {
        return kategori;
    }

    public String getUrun() {
        return urun;
    }

    public double getFiyat() {
        return fiyat;
    }
}
