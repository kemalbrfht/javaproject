class FoodItem extends MenuItem {
    public FoodItem(String name, double price) {
        super(name, price);
    }

    @Override
    public String getDescription() {
        return "Food: " + getName() + " - Price: " + getPrice();
    }
}
