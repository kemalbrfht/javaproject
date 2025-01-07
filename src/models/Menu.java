package models;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<String, Map<String, Product>> products;

    public Menu() {
        products = new HashMap<>();

        // Main Dishes
        Map<String, Product> mainDishes = new HashMap<>();
        mainDishes.put("Mantı", new Product(50.0, "/src/images/manti.jpg", 10));
        mainDishes.put("Köfte", new Product(60.0, "/src/images/kofte.jpg", 20));
        mainDishes.put("Lahmacun", new Product(40.0, "/src/images/lahmacun.jpg", 5));
        mainDishes.put("Zeytinyağlı Sarma", new Product(45.0, "/src/images/sarma.jpg", 3));
        mainDishes.put("Pide", new Product(55.0, "/src/images/pide.jpg", 8));
        mainDishes.put("Döner ve İskender", new Product(70.0, "/src/images/doner_iskender.jpg", 9));
        mainDishes.put("et kavurma", new Product(70.0, "/src/images/et_kavurma.jpg", 9));
        mainDishes.put("hamsi baligi", new Product(70.0, "/src/images/hamsi_baligi.jpg", 9));
        mainDishes.put("kuru fasulye", new Product(70.0, "/src/images/kuru_fasulye.jpg", 9));
        mainDishes.put("kusbasili pide", new Product(70.0, "/src/images/kusbasili_pide.jpg", 9));
        mainDishes.put("somon baligi", new Product(70.0, "/src/images/somon_baligi.jpg", 9));

        products.put("Main Dish", mainDishes);

        // Drinks
        Map<String, Product> drinks = new HashMap<>();
        drinks.put("Su", new Product(10.0, "/src/images/su.jpg", 0));
        drinks.put("Ayran", new Product(15.0, "/src/images/ayran.jpg", 0));
        drinks.put("Kola", new Product(20.0, "/src/images/kola.jpg", 0));
        drinks.put("Meyvesuyu", new Product(50.0, "/src/images/meyvesuyu.jpg", 0));
        drinks.put("çay", new Product(50.0, "/src/images/cay.jpg", 0));
        drinks.put("filtre kahve", new Product(50.0, "/src/images/filtre_kahve.jpg", 0));
        drinks.put("latte", new Product(50.0, "/src/images/latte.jpg", 0));
        drinks.put("şalgam", new Product(50.0, "/src/images/salgam.jpg", 0));

        products.put("Drink", drinks);

        // Desserts
        Map<String, Product> desserts = new HashMap<>();
        desserts.put("Baklava", new Product(30.0, "/src/images/baklava.jpg", 10));
        desserts.put("Magnolia", new Product(25.0, "/src/images/magnolia.jpg", 5));
        desserts.put("Sütlaç", new Product(20.0, "/src/images/sutlac.jpg", 5));
        desserts.put("Güllac", new Product(20.0, "/src/images/gullac.jpg", 5));
        desserts.put("mozaik pasta", new Product(20.0, "/src/images/mozaik_pasta.jpg", 5));
        desserts.put("profiterol", new Product(20.0, "/src/images/profiterol.jpg", 5));
        desserts.put("trilece", new Product(20.0, "/src/images/trilece.jpg", 5));
        desserts.put("waffle", new Product(20.0, "/src/images/waffle.jpg", 5));

        products.put("Dessert", desserts);

        // Pasta
        Map<String, Product> pasta = new HashMap<>();
        pasta.put("İtalyan makarna", new Product(50, "/src/images/italyan_makarna.jpg", 20));
        pasta.put("Fırında makarna", new Product(50, "/src/images/firinda_makarna.jpg", 20));
        pasta.put("kremalı makarna", new Product(50, "/src/images/kremalimkr.jpg", 20));
        pasta.put("soslu makarna", new Product(50, "/src/images/soslumkr.jpg", 20));

        products.put("Pasta", pasta);

        // Salads
        Map<String, Product> salads = new HashMap<>();
        salads.put("Kısır", new Product(10.0, "/src/images/kisir.jpg", 0));
        salads.put("brokoli salatası", new Product(10, "/src/images/brokolisalatasi.jpg", 4));
        salads.put("Humus", new Product(100, "/src/images/humus.jpg", 6));
        salads.put("Tavuk salatası", new Product(16, "/src/images/tavuksalata.jpg", 30));
        salads.put("Yıldız şehriye", new Product(18, "/src/images/yildizsehriye.jpg", 30));

        products.put("Salads", salads);

        // Soups
        Map<String, Product> soups = new HashMap<>();
        soups.put("kelle paça çorbası", new Product(20.0, "/src/images/kelle_paca_corbasi.jpg", 0));
        soups.put("mercimek çorbası", new Product(30.0, "/src/images/mercimek_corbasi.jpg", 0));
        soups.put("tarhana çorbası", new Product(50.0, "/src/images/tarhana_corbasi.jpg", 0));
        soups.put("tavuk çorbası", new Product(70.0, "/src/images/tavuk_corbasi.jpg", 0));

        products.put("Soup", soups);
    }

    public Map<String, Product> getCategory(String category) {
        return products.get(category);
    }

    public void showMenu() {
        for (String category : products.keySet()) {
            System.out.println("\n" + category + ":");
            for (Map.Entry<String, Product> product : products.get(category).entrySet()) {
                System.out.println("- " + product.getKey() + " (" + product.getValue().getPrice() + " TL, Image: " + product.getValue().getImagePath() + ")");
            }
        }
    }

    public static class Product extends Order {
        private String imagePath;

        public Product(double price, String imagePath, int preparationTime) {
            super("Category", "Product", price, preparationTime);
            this.imagePath = imagePath;
        }

        public String getImagePath() {
            return imagePath;
        }

        @Override
        public void prepare() {
        }
    }
}