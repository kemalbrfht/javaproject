package models;

public class FoodOrder extends Order {
    public FoodOrder(String category, String product, double price, int preparationTime) {
        super(category, product, price, preparationTime);
    }

    @Override
    public void prepare() {
        // Preparation logic
    }
}