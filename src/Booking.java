import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A booking for a table in a restaurant. A booking contains a date, start time, number of people and restaurant ID, all of which are decided by
 * the user.
 * The booking also contains some internal data like bookingID which will be handled by the Restaurant class and the end time which by default is the
 * start time + 2 hours.
 *
 * @author Blake
 */

public abstract class Booking {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int numberOfPeople;
    private int tableID;
    private int restaurantID;
    private int bookingID;

    /**
     * Creates a Booking with the specified date, start time, number of people, table ID and restaurant ID
     *
     * @param date           Date of the booking
     * @param startTime      Time the booking starts at
     * @param numberOfPeople Number of people the booking is for
     * @param tableID        ID of the table the booking is for
     * @param restaurantID   ID of the restaurant the booking is for
     * @author Blake
     */
    protected Booking(LocalDate date, LocalTime startTime, int numberOfPeople, int tableID, int restaurantID) {
        this.date = date;
        this.startTime = startTime;
        endTime = startTime.plusHours(2);
        this.numberOfPeople = numberOfPeople;
        this.tableID = tableID;
        this.restaurantID = restaurantID;
    }

    /**
     * Gets the date of the booking
     *
     * @return Date of the booking
     * @author Blake
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the booking
     *
     * @param date The date to set the booking to
     * @author Blake
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the start time of the booking
     *
     * @return The start time of the booking
     * @author Blake
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the booking. Also changes the end time to start time + 2 hours
     *
     * @param startTime The time to set the bookings start time to
     * @author Blake
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
        this.endTime = startTime.plusHours(2);
    }

    /**
     * Gets the end time of the booking
     *
     * @return The time the booking ends at
     * @author Blake
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Gets the number of people the booking is for
     *
     * @return The number of people the booking is for
     * @author Blake
     */
    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    /**
     * Sets the number of people the booking is for
     *
     * @param numberOfPeople The number of people the booking will be set for
     * @author Blake
     */
    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    /**
     * Gets the ID of the table the booking is made for
     *
     * @return The ID of the table the booking is made for
     * @author Blake
     */
    public int getTableID() {
        return tableID;
    }

    /**
     * Sets the ID of the table the booking is made for
     *
     * @param tableID The ID of the table the booking will be set for
     * @author Blake
     */
    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    /**
     * Gets the ID of the restaurant the booking is in
     *
     * @return The ID of the restaurant the booking is in
     * @author Blake
     */
    public int getRestaurantID() {
        return restaurantID;
    }

    /**
     * Sets the ID of the restaurant the booking is made for
     *
     * @param restaurantID The ID of the restaurant the booking will be set to
     * @author Blake
     */
    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    /**
     * Gets the ID of the booking
     *
     * @return The ID of the booking
     * @author Blake
     */
    public int getBookingID() {
        return bookingID;
    }

    /**
     * Sets the ID of the booking
     *
     * @param bookingID The ID that the booking will be set to
     * @author Blake
     */
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    @Override
    public String toString() {
        return String.format("""
                             Date: %s
                             Time: %s
                             Number of People: %d
                             Table number: %d
                                                          
                             """, date, startTime.format(DateTimeFormatter.ofPattern("HH:mm")), numberOfPeople, tableID);
    }
}
