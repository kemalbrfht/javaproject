package controllers;
import java.util.ArrayList;
import java.util.List;
import models.*;

public class RestaurantManagement {
    private List<Table> tables;
    private double revenue;
    private Kitchen kitchen;

    public RestaurantManagement() {
        tables = new ArrayList<>();
        revenue = 0.0;
        for (int i = 1; i <= 20; i++) {
            tables.add(new Table(i));
        }
    }

    public void addToRevenue(double amount) {
        revenue += amount;
    }

    public double getRevenue() {
        return revenue;
    }

    // Returns the list of tables
    public List<Table> getTables() {
        return tables;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }

    public void prepareOrder(Preparable order) {
        order.prepare();
    }
}