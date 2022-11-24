import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A restaurant. Each restaurant has a unique identifier, name, address and phone number.
 * It has a bookingID tracker which auto increments upon adding a new booking to a table in the restaurant.
 * It contains a list of tables in the restaurant along with a list of menus that contain details about the food being served.
 *
 * @author Blake
 * @author Callum
 */

public class Restaurant {
    private int ID;
    private String name;
    private String address;
    private String phoneNumber;
    private int bookingID = 0;
    private final ArrayList<Table> tables = new ArrayList<>();
    private ArrayList<Menu> menu = new ArrayList<>();
    private FileWriter writer;

    /**
     * Makes a default restaurant with the specified ID, name, address and phone number.
     *
     * @param ID          ID of the restaurant
     * @param name        Name of the restaurant
     * @param address     Address of the restaurant
     * @param phoneNumber Phone number of the restaurant
     * @author Blake
     */

    public Restaurant(int ID, String name, String address, String phoneNumber) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


    /**
     * Gets the ID of the restaurant
     *
     * @return int ID of the restaurant
     * @author Blake
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the ID of the restaurant
     *
     * @param ID int to change the restaurant to
     * @author Blake
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Gets the name of the restaurant
     *
     * @return String name of the restaurant
     * @author Blake
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the restaurant
     *
     * @param name String to change the name to
     * @author Blake
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the address of the restaurant
     *
     * @return String address of the restaurant
     * @author Blake
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the restaurant
     *
     * @param address String to change the address to
     * @author Blake
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the phoneNumber of the restaurant
     *
     * @return String phone number of the restaurant
     * @author Blake
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the restaurant
     *
     * @param phoneNumber String to change the phone number to
     * @author Blake
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the list of tables in the restaurant
     *
     * @return ArrayList of the tables in the restaurant
     * @author Blake
     */
    public ArrayList<Table> getTables() {
        return tables;
    }

    /**
     * Adds a table to the restaurant
     *
     * @param ID       int ID of the table to be added
     * @param capacity int capacity of the table to be added
     * @author Blake
     */
    public void addTable(int ID, int capacity) {
        tables.add(new Table(ID, capacity));

        try {
            writer = new FileWriter("Tables.csv", true);
            writer.write(String.format("%d,%d,%d\n", getID(), ID, capacity));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Removes a table from the restaurant
     *
     * @param ID int ID of the table to be removed
     * @author Blake
     */
    public void removeTable(int ID) {
        tables.removeIf(table -> table.getID() == ID);

        try {
            String table = String.format("%d,%d", getID(), ID);
            Path tables = Path.of("Tables.csv");
            Scanner scanner = new Scanner(tables);
            writer = new FileWriter("temp.csv");
            while (scanner.hasNext()) {
                String nl = scanner.nextLine();
                if (nl.equals("")) continue;
                if (!nl.substring(0, nl.lastIndexOf(",")).equals(table)) writer.write(nl + "\n");
            }
            scanner.close();
            writer.close();
            Path temp = Path.of("temp.csv");
            Files.copy(temp, tables, StandardCopyOption.REPLACE_EXISTING);
            Files.delete(temp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a booking to a table in the restaurant
     *
     * @param booking Booking to be added
     * @author Blake
     */
    public void addBooking(Booking booking) {
        booking.setBookingID(++bookingID);
        tables.get(booking.getTableID()).addBooking(booking);
    }

    /**
     * Removes a booking from a table in the restaurant
     *
     * @param booking Booking to be removed
     * @author Blake
     */
    public void removeBooking(Booking booking) {
        tables.get(booking.getTableID()).removeBooking(booking);
    }

    /**
     * Gets a Booking from the restaurant using the specified bookingID.
     * Throws error if no booking is found.
     *
     * @param bookingID int ID of the booking to be returned
     * @return Booking that was looked for, if it exists
     * @throws Exception If no booking was found
     * @author Blake
     */
    public Booking getBooking(int bookingID) throws Exception {
        for (Table table : tables) {
            for (Booking booking : table.getBookings()) {
                if (booking.getBookingID() == bookingID) return booking;
            }
        }
        throw new Exception("Error: Could not find a booking with the specified ID");
    }

    /**
     * Gets a Booking from the restaurant using the specified date, time and phone number.
     * Throws error if no booking is found.
     *
     * @param date        LocalDate date of the booking
     * @param time        LocalTime time of the booking
     * @param phoneNumber String phone number of the booking
     * @return Booking that was looked for, if present
     * @throws Exception If no booking was found
     * @author Blake
     */
    public Booking getBooking(LocalDate date, LocalTime time, String phoneNumber) throws Exception {
        for (Table table : tables) {
            for (Booking booking : table.getBookings()) {
                if (booking.getDate().isEqual(date) && booking.getStartTime().equals(time) && ((Reservation) booking).getPhoneNumber()
                                                                                                                     .equals(phoneNumber)) {
                    return booking;
                }
            }
        }
        throw new Exception("Error: Could not find a booking with the specified ID");
    }

    /**
     * Replaces a reservation that is currently in the system with a new reservation.
     * Reservations keep the same name, phone number and bookingID, every other detail may change such as date, time or tableID may change.
     *
     * @param oldReservation The Reservation to be replaced
     * @param newReservation The new Reservation to replace with
     * @author Blake
     */
    public void updateReservation(Reservation oldReservation, Reservation newReservation) {
        newReservation.setName(oldReservation.getName());
        newReservation.setPhoneNumber(oldReservation.getPhoneNumber());
        newReservation.setBookingID(oldReservation.getBookingID());
        removeBooking(oldReservation);
        tables.get(newReservation.getTableID()).addBooking(newReservation);
    }

    /**
     * Gets all available reservations that could be made over a 2-week time period for a table that fits the number of people requested.
     * Timestamps for each day are hourly from 12:00 - 20:00.
     * Reservations will need to be made 2 hours in advance, and as such making a reservation for a day closes at 18:00.
     * If the current time of the start day is >= 11:00, then times for that day will start after 12:00.
     * If the current time of the start day is >= 18:00 then the start day will be the current day + 1 and times will start at 12:00.
     *
     * @param numberOfPeople int number of people that the reservations will be made for.
     * @return An ArrayList containing all possible Reservations for a 2-week time period.
     * @author Blake
     */
    public ArrayList<Reservation> getAvailableReservations(int numberOfPeople) {
        ArrayList<Reservation> availableReservations = new ArrayList<>();
        LocalDate startDate = LocalDate.now();
        int hour = LocalTime.now().getHour() + 2;

        if (hour >= 20) {
            startDate = startDate.plusDays(1);
            hour = 12;
        } else {
            hour = Math.max(hour, 12);
        }
        LocalDate endDate = startDate.plusDays(14);

        while (!startDate.isAfter(endDate)) {
            while (hour <= 20) {
                for (Table table : tables) {
                    if (table.freeAtTime(startDate, LocalTime.of(hour, 0))) {
                        availableReservations.add(new Reservation(startDate, LocalTime.of(hour, 0), numberOfPeople, table.getID(), getID()));
                    }
                }
                hour++;
            }
            startDate = startDate.plusDays(1);
            hour = 12;
        }
        return availableReservations;
    }

    /**
     * Gets a WalkIn booking for the amount of people requested. If no tables are currently available throws an error.
     *
     * @param numberOfPeople int number of people to be seated.
     * @return WalkIn booking for the requested number of people
     * @throws Exception If no available table is found.
     * @author Blake
     */
    public WalkIn getWalkIn(int numberOfPeople) throws Exception {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        for (Table table : tables) {
            if (table.getCapacity() >= numberOfPeople && table.freeAtTime(date, time)) {
                return new WalkIn(date, time, numberOfPeople, table.getID(), getID());
            }
        }
        throw new Exception("Could not find a free table");
    }

    public ArrayList<Menu> getMenu() {
        return menu;
    }

    public void addMenu(int ID, String category, String item, int price) {
        menu.add(new Menu(ID, category, item, price));
    }

    @Override
    public String toString() {
        return String.format("%s\n%s\n%s\n", getName(), getAddress(), getPhoneNumber());
    }
}
