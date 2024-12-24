import java.util.ArrayList;
import java.util.List;

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
