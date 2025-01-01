
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<String, Map<String, Double>> urunler;

    public Menu() {
        urunler = new HashMap<>();

        // Ana yemekler
        Map<String, Double> anaYemekler = new HashMap<>();
        anaYemekler.put("Mantı", 50.0);
        anaYemekler.put("Köfte", 60.0);
        anaYemekler.put("Lahmacun", 40.0);
        anaYemekler.put("Zeytinyağlı Sarma", 45.0);
        anaYemekler.put("Pide", 55.0);
        anaYemekler.put("Döner ve İskender", 70.0);
        urunler.put("Ana Yemek", anaYemekler);

        // İçecekler
        Map<String, Double> icecekler = new HashMap<>();
        icecekler.put("Su", 10.0);
        icecekler.put("Ayran", 15.0);
        icecekler.put("Kola", 20.0);
        urunler.put("İçecek", icecekler);

        // Tatlılar
        Map<String, Double> tatlilar = new HashMap<>();
        tatlilar.put("Baklava", 30.0);
        tatlilar.put("Magnolia", 25.0);
        tatlilar.put("Sütlaç", 20.0);
        urunler.put("Tatlı", tatlilar);
        
    }

    public Map<String, Double> getKategori(String kategori) {
        return urunler.get(kategori);
    }

    public void menuyuGoster() {
        for (String kategori : urunler.keySet()) {
            System.out.println("\n" + kategori + ":");
            for (Map.Entry<String, Double> urun : urunler.get(kategori).entrySet()) {
                System.out.println("- " + urun.getKey() + " (" + urun.getValue() + " TL)");
            }
        }
    }
}
