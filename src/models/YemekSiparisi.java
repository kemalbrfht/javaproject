package models;

public class YemekSiparisi extends Siparis{
    public YemekSiparisi(String kategori, String urun, double fiyat, int hazırlanmasuresi) {
        super(kategori, urun, fiyat, hazırlanmasuresi);
    }

    @Override
    public void hazirla() {
        // Hazırlama işlemleri
    }
}