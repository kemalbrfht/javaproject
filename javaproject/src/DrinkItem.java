class DrinkItem extends MenuItem {
    public DrinkItem(String name, double price) {
        super(name, price);
    }

    @Override
    public String getDescription() {
        return "Drink: " + getName() + " - Price: " + getPrice();
    }
}
