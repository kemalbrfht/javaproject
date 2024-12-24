import java.util.ArrayList;
import java.util.List;

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
