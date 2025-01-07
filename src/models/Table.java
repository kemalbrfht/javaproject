package models;

import controllers.*;
import java.util.ArrayList;

public class Table {
    private int tableNo;
    private boolean occupied;
    private ArrayList<Order> orders;
    private Kitchen kitchen;
    private String billNote;

    public String getBillNote() {
        return billNote;
    }

    public void setBillNote(String billNote) {
        this.billNote = billNote;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    public Table(int tableNo) {
        this.tableNo = tableNo;
        this.occupied = false;
        this.orders = new ArrayList<>();
        this.billNote = "";
    }

    public int getTableNo() {
        return tableNo;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void openTable() {
        if (!occupied) {
            occupied = true;
            System.out.println("Table " + tableNo + " opened.");
        } else {
            System.out.println("Table is already occupied!");
        }
    }

    public void closeTable() {
        occupied = false;
        billNote = "";
        orders.clear();
        System.out.println("Table " + tableNo + " closed.");
    }

    public double calculateBill() {
        double total = 0.0;
        for (Order order : orders) {
            total += order.getPrice(); // Sum up the price of each order
        }
        return total;
    }
}