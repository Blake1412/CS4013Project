import java.util.ArrayList;

public class Order {
    private final ArrayList<MenuItem> items = new ArrayList<>();

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    public double getTotalPrice() {
        double sum = 0;
        for (MenuItem item : items) {
            sum += item.getPrice();
        }
        return sum;
    }

    @Override
    public String toString() {
        return String.format("%s", items.toString().replace("[", ">").replace("]", "").replace(", ", "\n>"));
    }
}
