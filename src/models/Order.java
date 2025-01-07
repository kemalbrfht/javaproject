package models;

import java.util.Timer;

public abstract class Order implements Preparable {
    private String category;
    private String product;
    private double price;
    private int preparationTime;
    private boolean prepared;
    private int remainingPreparationTime; // Remaining preparation time
    private Timer timer;

    public Order(String category, String product, double price, int preparationTime) {
        this.category = category;
        this.timer = timer;
        this.product = product;
        this.price = price;
        this.preparationTime = preparationTime;
        this.prepared = false;
        this.remainingPreparationTime = preparationTime; // Initially, the remaining time is the same as the total time
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public int getRemainingPreparationTime() {
        return remainingPreparationTime;
    }

    public void setRemainingPreparationTime(int remainingPreparationTime) {
        this.remainingPreparationTime = remainingPreparationTime;
    }

    public String getCategory() {
        return category;
    }

    public String getProduct() {
        return product;
    }

    public boolean isPrepared() {
        return prepared;
    }

    public double getPrice() {
        return price;
    }

    public void setPrepared(boolean prepared) {
        this.prepared = prepared;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void prepare() {
        // Implement preparation logic
    }
}