package models;

public abstract class Siparis implements Hazirlanabilir {
    private String kategori;
    private String urun;
    private double fiyat;
    private int hazırlanmasuresi;
    private boolean hazırlandı;
    private int kalanHazırlanmaSüresi;  // Hazırlık süresi azalan

    public Siparis(String kategori, String urun, double fiyat,int hazırlanmasuresi) {
        this.kategori = kategori;
        this.urun = urun;
        this.fiyat = fiyat;
        this.hazırlanmasuresi= hazırlanmasuresi;
        this.hazırlandı = false;
        this.kalanHazırlanmaSüresi = hazırlanmasuresi;  // Başlangıçta kalan süre, toplam süre ile aynı
    }

    public int getHazırlanmasüresi(){
        return hazırlanmasuresi;
    }

    public int getKalanHazırlanmaSüresi() {
        return kalanHazırlanmaSüresi;
    }

    public void setKalanHazırlanmaSüresi(int kalanHazırlanmaSüresi) {
        this.kalanHazırlanmaSüresi = kalanHazırlanmaSüresi;
    }

    public String getKategori() {
        return kategori;
    }

    public String getUrun() {
        return urun;
    }

    public boolean isHazırlandı() {
        return hazırlandı;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setHazırlandı(boolean hazırlandı) {
        this.hazırlandı = hazırlandı;
    }

    @Override
    public void hazirla() {
        // Implement preparation logic
    }
}