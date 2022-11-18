import java.util.ArrayList;

public class Table {

    private int ID;
    private int capacity;
    private final ArrayList<Booking> bookings = new ArrayList<>();

    public Table(int ID, int capacity) {
        this.ID = ID;
        this.capacity = capacity;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public boolean addBooking(Booking booking) {
        for (Booking booking1 : bookings) {
            if (booking1.overlap(booking)) return false;
        }
        bookings.add(booking);
        booking.setTableID(getID());
        return true;
    }
}
