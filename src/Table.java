import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * A table in a restaurant. A table has an ID used to reference the table, a capacity of how much people it can seat, and a list of bookings that
 * are assigned to the table for different times.
 *
 * @author Blake
 */
public class Table {

    private int ID;
    private int capacity;
    private final ArrayList<Booking> bookings = new ArrayList<>();

    /**
     * Creates a table with the specified ID and capacity
     *
     * @param ID       The ID of the table to be created
     * @param capacity The capacity of the table to be created
     * @author Blake
     */
    public Table(int ID, int capacity) {
        this.ID = ID;
        this.capacity = capacity;
    }

    /**
     * Gets the ID of the table
     *
     * @return The ID of the table
     * @author Blake
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the ID of the table
     *
     * @param ID The ID the table will be set to
     * @author Blake
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Gets the capacity of the table
     *
     * @return The capacity of the table
     * @author Blake
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the table
     *
     * @param capacity The capacity the table will be set to
     * @author Blake
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets the list of bookings assigned to the table.
     *
     * @return The list of bookings assigned to the table.
     * @author Blake
     */
    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    /**
     * Adds a booking to the list of bookings assigned to the table
     *
     * @param booking The booking to be added
     * @author Blake
     */
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    /**
     * Removes a booking from the list of bookings assigned to the tanle
     *
     * @param booking The booking to remove
     * @author Blake
     */
    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    /**
     * Returns whether a table is free for a booking at a certain time.
     *
     * @param date The date of the instance to be checked
     * @param time The time of the instance to be checked
     * @return True if the range of time from date time - date time + 2 hours does not overlap with any other booking the table has. Otherwise, false.
     * @author Blake
     */
    public boolean freeAtTime(LocalDate date, LocalTime time) {
        for (Booking booking : bookings) {
            if (booking.getDate().isEqual(date) &&
                Utils.timeOverlap(time, time.plusHours(2), booking.getStartTime(), booking.getEndTime())) return false;
        }
        return true;
    }
}
