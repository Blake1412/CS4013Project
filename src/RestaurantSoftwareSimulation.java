import java.util.ArrayList;

/**
 * Main class for running the software
 *
 * @author Blake
 */
public class RestaurantSoftwareSimulation {
    public static void main(String[] args) {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(0, "Greg's", "123 Baker Street, Limerick", "087 012 456"));
        restaurants.add(new Restaurant(1, "Street View", "14 Parkway Rd, Limerick", "087 914 665"));

        Menu main = new Menu(0, "Main Course");
        main.addItem(new MenuItem(10, "Steak"));
        main.addItem(new MenuItem(5, "Burger"));
        main.addItem(new MenuItem(3.50, "Fish"));

        Menu drinks = new Menu(1, "Drinks");
        drinks.addItem(new MenuItem(2, "Water"));
        drinks.addItem(new MenuItem(20, "Wine"));
        drinks.addItem(new MenuItem(3, "Coca-Cola"));

        restaurants.forEach(restaurant -> {
            restaurant.getMenus().add(main);
            restaurant.getMenus().add(drinks);
        });

        RestaurantSoftwareInterface restaurantSoftwareInterface = new RestaurantSoftwareInterface(restaurants);
        restaurantSoftwareInterface.run();
    }
}
