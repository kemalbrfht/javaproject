package controllers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import models.Table;
import models.Order;

public class Kitchen {
    private List<Order> meals;
    private Map<Table, String> billNotes; // Structure to hold table and bill notes

    public Kitchen() {
        meals = new ArrayList<>();
        new Timer();
        billNotes = new HashMap<>();
    }

    // Method to add meals
    public void addMeals(ArrayList<Order> meals) {
        this.meals.addAll(meals);
    }

    // Add bill note for a table
    public void addBillNote(Table table) {
        billNotes.put(table, table.getBillNote());
    }

    // View bill note
    public String getBillNote(Table table) {
        return billNotes.getOrDefault(table, "No bill note");
    }

    // Simulate the preparation of meals
    public void prepareMeals() {
        for (Order order : meals) {
            // Skip if the meal is already prepared or the timer is already running
            if (order.isPrepared() || order.getTimer() != null) {
                continue;
            }

            // Create a TimerTask for meal preparation time
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    int remainingTime = order.getRemainingPreparationTime();
                    if (remainingTime > 0) {
                        remainingTime -= 1; // Decrease the remaining time each second
                        order.setRemainingPreparationTime(remainingTime); // Update remaining time
                    } else {
                        System.out.println(order.getProduct() + " is prepared!");
                        order.setPrepared(true); // Mark the meal as prepared
                        order.getTimer().cancel(); // Cancel the timer
                        order.setTimer(null); // Reset the timer reference
                    }
                }
            };

            // Create a new Timer and assign it to the order
            Timer timer = new Timer();
            order.setTimer(timer);
            timer.scheduleAtFixedRate(timerTask, 0, 1000); // Run every second
        }
    }

    // Return the list of meals
    public List<Order> getMeals() {
        return meals;
    }
}