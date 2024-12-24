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
