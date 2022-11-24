import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * UI class for the restaurant software. Takes in an ArrayList of restaurants and starts the UI through the run() method.
 *
 * @author Blake
 */
public class RestaurantSoftwareInterface {
    private final ArrayList<Restaurant> restaurants;
    private final Scanner scanner;
    private Restaurant restaurant;

    /**
     * Constructs a software instance with the given restaurant list
     *
     * @param restaurants The chain of restaurants that the software will work with
     * @author Blake
     */
    public RestaurantSoftwareInterface(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
        scanner = new Scanner(System.in);
    }

    /**
     * Launches the software
     *
     * @author Blake
     */
    public void run() {
        displayRestaurantSelectionPage();
    }

    /**
     * Displays the opening page where the user will select which restaurant they wish to continue with
     *
     * @author Blake
     */
    private void displayRestaurantSelectionPage() {
        System.out.println("Welcome to Yum's restaurant chain!\nPlease Select a restaurant below:\n");
        restaurant = getRestaurant();
        displayRestaurantHomepage();
    }

    /**
     * Displays the home page for the selected restaurant
     *
     * @author Blake
     */
    private void displayRestaurantHomepage() {
        System.out.println("""
                           What would you like to do?
                           A) Make Reservation
                           B) Edit Reservation
                           C) Staff Login
                           D) Back
                           Z) Exit""");
        switch (scanner.next().toUpperCase()) {
            case "A" -> displayMakeReservationPage();
            case "B" -> displayFindReservationPage();
            case "C" -> displayStaffLoginPage();
            case "D" -> displayRestaurantSelectionPage();
            case "Z" -> System.exit(0);
        }
    }

    /**
     * Displays the page used to make reservations
     *
     * @author Blake
     */
    private void displayMakeReservationPage() {
        try {
            Reservation reservation = getReservation();

            scanner.nextLine();
            System.out.println("Please enter your name");
            String name = scanner.nextLine();
            reservation.setName(name);
            System.out.println("Please enter your phone number");
            String phoneNumber = scanner.nextLine();
            reservation.setPhoneNumber(phoneNumber);
            restaurant.addBooking(reservation);

            System.out.printf("""
                              Reservation success! Your reservation details are as follows:
                              %sYour booking ID is: %d
                              Please remember this in case you need to update or cancel your reservation
                                                        
                              """, reservation, reservation.getBookingID());

            makeReservationOptionsPage("What would you like to do?");
        } catch (Exception e) {
            makeReservationOptionsPage(e.getMessage());
        }
    }

    /**
     * Displays the page used to find a reservation in the system
     *
     * @author Blake
     */
    private void displayFindReservationPage() {
        Reservation reservation;
        System.out.println("""
                           Please choose a method to select your reservation
                           A) Booking ID
                           B) Personal Details (Phone number, date and time)
                           C) Back
                           Z) Exit
                           """);
        switch (scanner.next().toUpperCase()) {
            case "A" -> {
                System.out.println("Please enter your booking ID");
                try {
                    reservation = (Reservation) restaurant.getBooking(scanner.nextInt());
                    System.out.printf("Booking found.\nBooking details:\n%s", reservation);
                    displayEditReservationPage(reservation);
                } catch (Exception e) {
                    System.out.println("Could not find a reservation with given ID\n");
                    displayFindReservationPage();
                }
            }
            case "B" -> {
                scanner.nextLine();
                System.out.println("Enter your phone number");
                String phoneNumber = scanner.nextLine();

                System.out.println("Select the date of your reservation");
                ArrayList<LocalDate> dates = new ArrayList<>();
                ArrayList<LocalTime> times = new ArrayList<>();
                LocalDate date = LocalDate.now();

                if (LocalTime.now().getHour() >= 18) {
                    date = date.plusDays(1);
                }

                int i;
                for (i = 0; i <= 14; i++) {
                    dates.add(date);
                    date = date.plusDays(1);
                }
                date = (LocalDate) getSelection(dates);

                System.out.println("Select the time of your reservation");
                if (date.isEqual(LocalDate.now())) i = Math.max(LocalTime.now().getHour(), 10);
                else i = 10;
                while (i <= 18) {
                    times.add(LocalTime.of(i + 2, 0));
                    i++;
                }
                LocalTime time = (LocalTime) getSelection(times);

                try {
                    reservation = (Reservation) restaurant.getBooking(date, time, phoneNumber);
                    System.out.printf("Booking found\nBooking details:\n%s", reservation);
                    displayEditReservationPage(reservation);
                } catch (Exception e) {
                    System.out.println("Could not find a booking with the given details");
                    displayFindReservationPage();
                }
            }
            case "C" -> displayRestaurantHomepage();
            case "Z" -> System.exit(0);
        }
    }

    /**
     * Displays the page used to log in as a staff member
     *
     * @author Blake
     */
    private void displayStaffLoginPage() {
        System.out.println("Enter your username");
        String username = scanner.next();
        System.out.println("Enter your password");
        String password = scanner.next();
        if (staffLogin(username, password)) {
            System.out.println("Login successful");
            displayStaffHomepage();
        } else {
            System.out.println("""
                               Incorrect username or password
                               A) Retry
                               B) Cancel
                               """);
            switch (scanner.next().toUpperCase()) {
                case "A" -> displayStaffLoginPage();
                case "B" -> displayRestaurantHomepage();
            }
        }
    }

    /**
     * Displays the homepage for a logged in staff member
     *
     * @author Blake
     */
    private void displayStaffHomepage() {
        System.out.println("""
                           What would you like to do?
                           A) Take Walk-In
                           B) Add order
                           C) Complete order for table
                           D) Logout
                           Z) Exit
                           """);

        switch (scanner.next().toUpperCase()) {
            case "A" -> displayTakeWalkInPage();
            case "B" -> {
                System.out.println("Please select a table");
                Table table = (Table) getSelection(restaurant.getTables());
                Order order = displayCreateOrderPage(new Order());
                table.setOrder(order);
                displayStaffHomepage();
            }
            case "C" -> {
                System.out.println("Please select a table");
                ((Table) getSelection(new ArrayList<>(restaurant.getTables().stream()
                                                                .filter(table -> table.getOrder() != null)
                                                                .collect(Collectors.toList())))).completeOrder("Card");
                System.out.println("Order completed!");
                displayStaffHomepage();
            }
            case "D" -> displayRestaurantHomepage();
            case "Z" -> System.exit(0);
        }
    }

    /**
     * Displays the staff page to take a walk in customer
     *
     * @author Blake
     */
    private void displayTakeWalkInPage() {
        System.out.println("Enter number of people");
        int numberOfPeople = scanner.nextInt();
        try {
            WalkIn walkIn = restaurant.getWalkIn(numberOfPeople);
            System.out.printf("""
                              Table found, booking details:
                              %s""", walkIn);
            restaurant.addBooking(walkIn);
            displayStaffHomepage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            displayStaffHomepage();
        }
    }

    /**
     * Displays the staff page to create an order for a table
     *
     * @param order The order to be edited
     * @return The new order
     * @author Blake
     */
    private Order displayCreateOrderPage(Order order) {
        System.out.printf("""
                          %s
                                                     
                          Create an order
                          A) Add item
                          B) Remove item
                          C) Confirm order
                          Z) Cancel
                                                     
                          """, order);

        switch (scanner.next().toUpperCase()) {
            case "A" -> {
                System.out.println("Select a menu");
                Menu menu = (Menu) getSelection(restaurant.getMenus());
                System.out.println("Select an item");
                order.addItem((MenuItem) getSelection(menu.getItems()));
                displayCreateOrderPage(order);
            }
            case "B" -> {
                System.out.println("Select an item to remove");
                order.removeItem((MenuItem) getSelection(order.getItems()));
                displayCreateOrderPage(order);
            }
            case "C" -> {
                return order;
            }
            case "Z" -> displayStaffHomepage();
        }

        return order;
    }

    /**
     * Displays the page for a customer to edit a reservation
     *
     * @param reservation The reservation to be edited
     * @author Blake
     */
    private void displayEditReservationPage(Reservation reservation) {
        System.out.println("""
                           What would you like to do?
                           A) Edit Reservation
                           B) Cancel Reservation
                           C) Back
                           Z) Exit
                           """);

        switch (scanner.next().toUpperCase()) {
            case "A" -> {
                System.out.println("Please enter the details of your updated reservation:\n");
                try {
                    Reservation newReservation = getReservation();
                    restaurant.updateReservation(reservation, newReservation);
                    System.out.printf("""
                                      Reservation updated successfully
                                      Your new reservation details are:
                                      %s
                                      """, newReservation);
                    displayEditReservationPage(newReservation);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("""
                                       What would you like to do?
                                       A) Back
                                       Z) Exit
                                       """);
                    switch (scanner.next().toUpperCase()) {
                        case "A" -> displayEditReservationPage(reservation);
                        case "B" -> System.exit(0);
                    }
                }
            }
            case "B" -> {
                restaurant.removeBooking(reservation);
                System.out.println("Reservation removed successfully");
                displayRestaurantHomepage();
            }
            case "C" -> displayFindReservationPage();
            case "Z" -> System.exit(0);
        }
    }

    /**
     * Displays the options/troubleshooting page while making a reservation
     *
     * @param message The message to be outputted for troubleshooting
     * @author Blake
     */
    private void makeReservationOptionsPage(String message) {
        System.out.println(message);
        System.out.println("""
                           A) Make new reservation
                           B) Back
                           Z) Exit
                           """);
        switch (scanner.next().toUpperCase()) {
            case "A" -> displayMakeReservationPage();
            case "B" -> displayRestaurantHomepage();
            case "Z" -> System.exit(0);
        }
    }

    /**
     * Displays the page used to select a reservation from the system
     *
     * @return The selected reservation
     * @throws Exception If the reservation cannot be found
     * @author Blake
     */
    private Reservation getReservation() throws Exception {
        System.out.println("Enter the number of people you are making the reservation for\n*We only accept reservations for 2-12 people.*");
        int numberOfPeople = scanner.nextInt();
        while (numberOfPeople < 2 || numberOfPeople > 12) {
            System.out.println("Error: Please enter a number of people between 2 and 12");
            numberOfPeople = scanner.nextInt();
        }

        ArrayList<Reservation> reservations = restaurant.getAvailableReservations(numberOfPeople);

        if (reservations.size() == 0) {
            throw new Exception("""
                                Sorry, we do not have any available tables for this number of people in the foreseeable future.
                                Please change reservation details or try one of our other restaurants.
                                """);
        }

        LinkedHashSet<LocalDate> dates = new LinkedHashSet<>();
        reservations.forEach(reservation -> dates.add(reservation.getDate()));

        System.out.println("Please select an available date");
        LocalDate date = (LocalDate) getSelection(new ArrayList<>(dates));

        reservations = reservations.stream()
                                   .filter(res -> res.getDate().isEqual(date))
                                   .collect(Collectors.toCollection(ArrayList::new));

        if (reservations.size() == 0) {
            throw new Exception("""
                                Sorry, we do not have any available tables at the given date.
                                Please change your reservation details or try one of our other restaurants.
                                """);
        }

        LinkedHashSet<LocalTime> times = new LinkedHashSet<>();
        reservations.forEach(reservation -> times.add(reservation.getStartTime()));

        System.out.println("Please select an available time");
        LocalTime time = (LocalTime) getSelection(new ArrayList<>(times));

        return reservations.stream()
                           .filter(res -> res.getStartTime().equals(time))
                           .findFirst()
                           .orElseThrow();
    }

    /**
     * Utility method to take a list of any type of object and return a selected object from that list.
     *
     * @param objects An ArrayList of any type
     * @return An selected object from the list
     * @author Blake
     */
    private Object getSelection(ArrayList<?> objects) {
        int selection;
        while (true) {
            for (int i = 0; i < objects.size(); i++) {
                System.out.printf("%d) %s\n", i, objects.get(i));
            }
            selection = scanner.nextInt();
            if (selection < 0 || selection >= objects.size()) {
                System.out.println("Error: Invalid input");
            } else {
                return objects.get(selection);
            }
        }
    }

    /**
     * Utility method to get a restaurant from the list of restaurants available
     *
     * @return The selected restaurant
     * @author Blake
     */
    private Restaurant getRestaurant() {
        while (true) {
            for (int i = 0; i < restaurants.size(); i++) {
                System.out.printf("%d)\n%s\n", i, restaurants.get(i));
            }
            int input = scanner.nextInt();
            if (input >= 0 && input < restaurants.size()) return restaurants.get(input);
        }
    }


    /**
     * Utility method to verify a staff login to the system
     *
     * @param username String for the username of the user
     * @param password String for the password of the user
     * @return true if the given details match a user on the system, false otherwise
     */
    private boolean staffLogin(String username, String password) {
        try {
            Scanner loginScanner = new Scanner(new File("StaffLogins.csv"));
            loginScanner.nextLine();
            while (loginScanner.hasNext()) {
                String[] details = loginScanner.nextLine().split(",");
                if (username.equals(details[0]) &&
                    password.equals(details[1])) return true;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
