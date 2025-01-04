package models;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<String, Map<String, Urun>> urunler;

    public Menu() {
        urunler = new HashMap<>();

        // Ana yemekler
        Map<String, Urun> anaYemekler = new HashMap<>();
        anaYemekler.put("Mantı", new Urun(50.0, "/src/images/manti.jpg",10));
        anaYemekler.put("Köfte", new Urun(60.0, "/src/images/kofte.jpg",20));
        anaYemekler.put("Lahmacun", new Urun(40.0, "/src/images/lahmacun.jpg",5));
        anaYemekler.put("Zeytinyağlı Sarma", new Urun(45.0, "/src/images/sarma.jpg",3));
        anaYemekler.put("Pide", new Urun(55.0, "/src/images/pide.jpg",8));
        anaYemekler.put("Döner ve İskender", new Urun(70.0, "/srcimages/doner_iskender.jpg",9));
        anaYemekler.put("et kavurma", new Urun(70.0, "/srcimages/et_kavurma.jpg",9));
        anaYemekler.put("hamsi baligi", new Urun(70.0, "/srcimages/hamsi_baligi.jpg",9));
        anaYemekler.put("kuru fasulye", new Urun(70.0, "/srcimages/kuru_fasulye.jpg",9));
        anaYemekler.put("kusbasili pide", new Urun(70.0, "/srcimages/kusbasili_pide.jpg",9));
        anaYemekler.put("somon baligi", new Urun(70.0, "/srcimages/somon_baligi.jpg",9));

        urunler.put("Ana Yemek", anaYemekler);

        // İçecekler
        Map<String, Urun> icecekler = new HashMap<>();
        icecekler.put("Su", new Urun(10.0, "/src/images/su.jpg",0));
        icecekler.put("Ayran", new Urun(15.0, "/src/images/ayran.jpg",0));
        icecekler.put("Kola", new Urun(20.0, "/src/images/kola.jpg",0));
        icecekler.put("Meyvesuyu", new Urun(50.0, "/src/images/meyvesuyu.jpg",0));
        icecekler.put("cay", new Urun(50.0, "/src/images/cay.jpg",0));
        icecekler.put("filtre kahve", new Urun(50.0, "/src/images/filtre_kahve.jpg",0));
        icecekler.put("latte", new Urun(50.0, "/src/images/latte.jpg",0));
        icecekler.put("salgam", new Urun(50.0, "/src/images/salgam.jpg",0));

        urunler.put("İçecek", icecekler);

        // Tatlılar
        Map<String, Urun> tatlilar = new HashMap<>();
        tatlilar.put("Baklava", new Urun(30.0, "/src/images/baklava.jpg",10));
        tatlilar.put("Magnolia", new Urun(25.0, "/src/images/magnolia.jpg",5));
        tatlilar.put("Sütlaç", new Urun(20.0, "/src/images/sutlac.jpg",5));
        tatlilar.put("Güllac", new Urun(20.0, "/src/images/Güllac.jpg",5));
        tatlilar.put("mozaik pasta", new Urun(20.0, "/src/images/mozaik_pasta.jpg",5));
        tatlilar.put("profiterol", new Urun(20.0, "/src/images/profiterol.jpg",5));
        tatlilar.put("trilece", new Urun(20.0, "/src/images/trilece.jpg",5));
        tatlilar.put("waffle", new Urun(20.0, "/src/images/waffle.jpg",5));

        urunler.put("Tatlı", tatlilar);

        Map<String,Urun> makarna= new HashMap<>();
        makarna.put("İtalyan makarna",new Urun(50, "/src/images/İtalyan_makarna.jpg", 20));
        makarna.put("Firinda makarna",new Urun(50, "/src/images/Firinda_makarna.jpg", 20));
        makarna.put("kremalimkr",new Urun(50, "/src/images/kremalimkr.jpg", 20));
        makarna.put("soslumkr",new Urun(50, "/src/images/soslumkr.jpg", 20));
        
        urunler.put("Makarna",makarna);

        Map<String,Urun> salatalar= new HashMap<>();
        salatalar.put("Kısır",new Urun(10.0, "/src/images/kisir.jpg",0));
        salatalar.put("brokolisalatasi",new Urun(10, "/src/images/brokolisalatasi.jpg", 4));
        salatalar.put("Humus",new Urun(100, "/src/images/humus.jpg", 6));
        salatalar.put("Tavuksalata",new Urun(16, "/src/images/tavuksalata.jpg", 30));
        salatalar.put("YildizSehriye",new Urun(18, "/src/images/yildizsehriye.jpg", 30));

        urunler.put("salatalar",salatalar);

        Map<String,Urun> corbalar= new HashMap<>();
        
        corbalar.put("kelle paca corbasi",new Urun(20.0, "/src/images/kelle_paca_corbasi.jpg",0));
        corbalar.put("mercimek corbasi",new Urun(30.0, "/src/images/mercimek_corbasi.jpg",0));
        corbalar.put("tarhana corbasi",new Urun(50.0, "/src/images/tarhana_corbasi.jpg",0));
        corbalar.put("tavuk corbasi",new Urun(70.0, "/src/images/tavuk_corbasi.jpg",0));

        urunler.put("Çorbalar",corbalar);
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

    public static class Urun extends Siparis {
        private String gorselYolu;

        public Urun(double fiyat, String gorselYolu, int yemeksuresi) {
            super("Kategori", "Urun", fiyat, yemeksuresi);
            this.gorselYolu = gorselYolu;
        }

        public String getGorselYolu() {
            return gorselYolu;
        }

        @Override
        public void hazirla() {
            
        }
    }
}
