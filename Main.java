import java.util.List;
import java.util.ArrayList;

// Abstract Class for Menu Item
abstract class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract String getDescription();
}

// Concrete Class for Food Item
class FoodItem extends MenuItem {
    public FoodItem(String name, double price) {
        super(name, price);
    }

    @Override
    public String getDescription() {
        return "Food: " + getName() + " - Price: " + getPrice();
    }
}

// Concrete Class for Drink Item
class DrinkItem extends MenuItem {
    public DrinkItem(String name, double price) {
        super(name, price);
    }

    @Override
    public String getDescription() {
        return "Drink: " + getName() + " - Price: " + getPrice();
    }
}

// Interface for Order Operations
interface OrderOperations {
    void addItem(MenuItem item);
    void removeItem(MenuItem item);
    double calculateTotal();
}

// Order Class
class Order implements OrderOperations {
    private List<MenuItem> items;
    private String note;

    public Order() {
        this.items = new ArrayList<>();
        this.note = "";
    }

    @Override
    public void addItem(MenuItem item) {
        items.add(item);
    }

    @Override
    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    @Override
    public double calculateTotal() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public List<MenuItem> getItems() {
        return items;
    }
}

// Table Class
class Table {
    private int tableNumber;
    private boolean isOccupied;
    private Order order;

    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        this.isOccupied = false;
        this.order = new Order();
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupyTable() {
        this.isOccupied = true;
    }

    public void freeTable() {
        this.isOccupied = false;
        this.order = new Order();
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public Order getOrder() {
        return order;
    }
}

// Restaurant Class
class Restaurant {
    private List<Table> tables;
    private double totalEarnings;

    public Restaurant(int numberOfTables) {
        this.tables = new ArrayList<>();
        for (int i = 1; i <= numberOfTables; i++) {
            tables.add(new Table(i));
        }
        this.totalEarnings = 0;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void processPayment(Table table, boolean isCard) {
        double total = table.getOrder().calculateTotal();
        totalEarnings += total;
        System.out.println("Payment processed: " + total + (isCard ? " (Card)" : " (Cash)"));
        table.freeTable();
    }

    public double getTotalEarnings() {
        return totalEarnings;
    }
}

// Main Class
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
