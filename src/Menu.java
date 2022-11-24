import java.util.ArrayList;

public class Menu {
    private int menuID;
    private String category;
    private final ArrayList<MenuItem> items = new ArrayList<>();

    public Menu(int ID, String category) {
        this.menuID = ID;
        this.category = category;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }
}
