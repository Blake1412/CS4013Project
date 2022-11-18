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
    private final ArrayList<Table> tables = new ArrayList<>();
    private ArrayList<Menu> menu = new ArrayList<>();
    private FileWriter writer;

    private static int bookingID = 0;

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

    public static int getBookingID() {
        return bookingID;
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

    public boolean addWalkIn(LocalDate date, LocalTime time, int numberOfPeople) {
        return addBooking(new WalkIn(date, time, numberOfPeople, getID(), ++bookingID));
    }

    public boolean addReservation(LocalDate date, LocalTime time, int numberOfPeople, String name, String phoneNumber) {
        return addBooking(new Reservation(date, time, numberOfPeople, name, phoneNumber, getID(), ++bookingID));
    }

    public void updateBooking(int bookingID) {

    }

    private boolean addBooking(Booking booking) {
        for (Table table : tables) {
            if (table.addBooking(booking)) return true;
        }
        return false;
    }

    public ArrayList<Menu> getMenu(){
        return menu;
    }

    public void addMenu (int ID, String category, String item, int price ){
        menu.add(new Menu(ID, category, item, price));

    }

    @Override
    public String toString() {
        return String.format("%s\n%s\n%s\n", getName(), getAddress(), getPhoneNumber());
    }
}
