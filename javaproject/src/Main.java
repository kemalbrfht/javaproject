public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant(5);

        // Example menu items
        MenuItem pizza = new FoodItem("Pizza", 10.0);
        MenuItem burger = new FoodItem("Burger", 8.0);
        MenuItem coke = new DrinkItem("Coke", 2.5);
        MenuItem water = new DrinkItem("Water", 1.0);

        // Open a table and take an order
        Table table1 = restaurant.getTables().get(0);
        table1.occupyTable();
        table1.getOrder().addItem(pizza);
        table1.getOrder().addItem(coke);
        table1.getOrder().setNote("Extra cheese on pizza");

        System.out.println("Table 1 Order:");
        for (MenuItem item : table1.getOrder().getItems()) {
            System.out.println(item.getDescription());
        }
        System.out.println("Total: " + table1.getOrder().calculateTotal());

        // Process payment
        restaurant.processPayment(table1, true);

        // Show restaurant earnings
        System.out.println("Total Earnings: " + restaurant.getTotalEarnings());
    }
}
