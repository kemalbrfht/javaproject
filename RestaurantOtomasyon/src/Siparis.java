
public class Siparis {
    private String kategori;
    private String urun;
    private double fiyat;
    private int hazırlanmasuresi;

    public Siparis(String kategori, String urun, double fiyat,int hazırlanmasuresi) {
        this.kategori = kategori;
        this.urun = urun;
        this.fiyat = fiyat;
        this.hazırlanmasuresi= hazırlanmasuresi;
    }
    public int getHazırlanmasüresi(){
        return hazırlanmasuresi;
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
