import java.util.ArrayList;

public class RestaurantSoftwareSimulation {
    public static void main(String[] args) {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(0, "Gregs", "123 Baker Street, Limerick", "087 012 456"));
        restaurants.add(new Restaurant(1, "Street View", "14 Parkway Rd, Limerick", "087 914 665"));

        RestaurantSoftwareInterface restaurantSoftwareInterface = new RestaurantSoftwareInterface(restaurants);
        restaurantSoftwareInterface.run();
    }
}
