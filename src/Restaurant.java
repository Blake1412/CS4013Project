import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {
    private int ID;
    private String name;
    private String address;
    private String phoneNumber;
    private int bookingID = 0;
    private final ArrayList<Table> tables = new ArrayList<>();
    private ArrayList<Menu> menu = new ArrayList<>();
    private FileWriter writer;


    public Restaurant(int ID, String name, String address, String phoneNumber) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

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

    public void addBooking(Booking booking) {
        booking.setBookingID(++bookingID);
        tables.get(booking.getTableID()).addBooking(booking);
    }

    public void removeBooking(Booking booking) {
        tables.get(booking.getTableID()).removeBooking(booking);
    }

    public Booking getBooking(int bookingID) throws Exception {
        for (Table table : tables) {
            for (Booking booking : table.getBookings()) {
                if (booking.getBookingID() == bookingID) return booking;
            }
        }
        throw new Exception("Error: Could not find a booking with the specified ID");
    }

    public Booking getBooking(LocalDate date, LocalTime time, String phoneNumber) throws Exception {
        for (Table table : tables) {
            for (Booking booking: table.getBookings()) {
                if (booking.getDate().isEqual(date) && booking.getStartTime().equals(time) && ((Reservation) booking).getPhoneNumber().equals(phoneNumber)) {
                    return booking;
                }
            }
        }
        throw new Exception("Error: Could not find a booking with the specified ID");
    }

    public void updateReservation(Reservation oldReservation, Reservation newReservation) {
        newReservation.setName(oldReservation.getName());
        newReservation.setPhoneNumber(oldReservation.getPhoneNumber());
        newReservation.setBookingID(oldReservation.getBookingID());
        removeBooking(oldReservation);
        tables.get(newReservation.getTableID()).addBooking(newReservation);
    }

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

    public WalkIn getWalkIn(int numberOfPeople) throws Exception{
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
