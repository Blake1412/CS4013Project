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

    public boolean addBooking(Booking bookingOne) {
        for (Booking bookingTwo : bookings) {
            if (Utils.timeOverlap(bookingOne.getStartTime(), bookingOne.getEndTime(), bookingTwo.getStartTime(), bookingTwo.getEndTime()))
                return false;
        }
        bookings.add(bookingOne);
        bookingOne.setTableID(getID());
        return true;
    }

    public boolean freeAtTime(LocalDate date, LocalTime time) {
        for (Booking booking : bookings) {
            if (booking.getDate().isEqual(date) &&
                Utils.timeOverlap(time, booking.getStartTime(), time.plusHours(2), booking.getEndTime())) return false;
        }
        return true;
    }
}
