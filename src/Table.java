import java.time.LocalDate;
import java.time.LocalTime;
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

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public boolean freeAtTime(LocalDate date, LocalTime time) {
        for (Booking booking : bookings) {
            if (booking.getDate().isEqual(date) &&
                Utils.timeOverlap(time, booking.getStartTime(), time.plusHours(2), booking.getEndTime())) return false;
        }
        return true;
    }
}
