import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A reservation type of booking. A customer can make one of these up to two weeks in advance to reserve a table for a specific date and time.
 *
 * @author Blake
 */
public class Reservation extends Booking {

    private String name;
    private String phoneNumber;

    /**
     * Creates a Booking with the specified date, start time, number of people, table ID and restaurant ID
     *
     * @param date           The date of the reservation
     * @param startTime      The time of the reservation
     * @param numberOfPeople The number of people for the reservation
     * @param tableID        The table ID the reservation is set for
     * @param restaurantID   The restaurant ID the reservation is in
     * @author Blake
     */
    public Reservation(LocalDate date, LocalTime startTime, int numberOfPeople, int tableID, int restaurantID) {
        super(date, startTime, numberOfPeople, tableID, restaurantID);
    }

    /**
     * Creates a Booking with the specified date, start time, number of people, table ID, restaurant ID and booking ID
     *
     * @param date           The date of the reservation
     * @param startTime      The time of the reservation
     * @param numberOfPeople The number of people for the reservation
     * @param tableID        The table ID the reservation is set for
     * @param restaurantID   The restaurant ID the reservation is in
     * @param bookingID      The booking ID of the reservation
     * @author Blake
     */
    public Reservation(LocalDate date, LocalTime startTime, int numberOfPeople, int tableID, int restaurantID, int bookingID) {
        super(date, startTime, numberOfPeople, tableID, restaurantID);
        setBookingID(bookingID);
    }

    /**
     * Gets the name of the group the reservation is for
     *
     * @return The name of the group the reservation is for
     * @author Blake
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the group the reservation is for
     *
     * @param name The name of the group that the reservation will be set to
     * @author Blake
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the phone number of the reservation
     *
     * @return The phone number the reservation
     * @author Blake
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the reservation
     *
     * @param phoneNumber The phone number the reservation will be set to
     * @author Blake
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("""
                             Reservation
                             Date: %s
                             Time: %s
                             Number of People: %s
                             Name: %s
                             Phone Number: %s
                             Table number: %d
                                                          
                             """, getDate(), getStartTime(), getNumberOfPeople(), getName(), getPhoneNumber(), getTableID());
    }
}
