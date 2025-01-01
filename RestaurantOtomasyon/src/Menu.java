import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<String, Map<String, Urun>> urunler;

    public Menu() {
        urunler = new HashMap<>();

        // Ana yemekler
        Map<String, Urun> anaYemekler = new HashMap<>();
        anaYemekler.put("Mantı", new Urun(50.0, "/src/images/manti.jpg"));
        anaYemekler.put("Köfte", new Urun(60.0, "/src/images/kofte.jpg"));
        anaYemekler.put("Lahmacun", new Urun(40.0, "/src/images/lahmacun.jpg"));
        anaYemekler.put("Zeytinyağlı Sarma", new Urun(45.0, "/src/images/sarma.jpg"));
        anaYemekler.put("Pide", new Urun(55.0, "/src/images/pide.jpg"));
        anaYemekler.put("Döner ve İskender", new Urun(70.0, "/srcimages/doner_iskender.jpg"));
        urunler.put("Ana Yemek", anaYemekler);

        // İçecekler
        Map<String, Urun> icecekler = new HashMap<>();
        icecekler.put("Su", new Urun(10.0, "/src/images/su.jpg"));
        icecekler.put("Ayran", new Urun(15.0, "/src/images/ayran.jpg"));
        icecekler.put("Kola", new Urun(20.0, "/srcimages/kola.jpg"));
        urunler.put("İçecek", icecekler);

        // Tatlılar
        Map<String, Urun> tatlilar = new HashMap<>();
        tatlilar.put("Baklava", new Urun(30.0, "/src/images/baklava.jpg"));
        tatlilar.put("Magnolia", new Urun(25.0, "/src/images/magnolia.jpg"));
        tatlilar.put("Sütlaç", new Urun(20.0, "/src/images/sutlac.jpg"));
        urunler.put("Tatlı", tatlilar);
    }

    public Map<String, Urun> getKategori(String kategori) {
        return urunler.get(kategori);
    }

    public void menuyuGoster() {
        for (String kategori : urunler.keySet()) {
            System.out.println("\n" + kategori + ":");
            for (Map.Entry<String, Urun> urun : urunler.get(kategori).entrySet()) {
                System.out.println("- " + urun.getKey() + " (" + urun.getValue().getFiyat() + " TL, Görsel: " + urun.getValue().getGorselYolu() + ")");
            }
        }
    }

    public static class Urun {
        private double fiyat;
        private String gorselYolu;

        public Urun(double fiyat, String gorselYolu) {
            this.fiyat = fiyat;
            this.gorselYolu = gorselYolu;
        }

        public double getFiyat() {
            return fiyat;
        }

        public String getGorselYolu() {
            return gorselYolu;
        }
    }
}
