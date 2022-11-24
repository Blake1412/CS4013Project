import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A Walk-In type of booking. Takes a customer immediately without any reservation.
 *
 * @author Blake
 */
public class WalkIn extends Booking {

    /**
     * Creates a Walk-In booking with the specified date, time, number of people, table ID, and restaurant ID
     *
     * @param date           The date of the walk in booking
     * @param startTime      The time of the walk in booking
     * @param numberOfPeople The number of people for the walk in booking
     * @param tableID        The table ID the walk in booking is set for
     * @param restaurantID   The restaurant ID the walk in booking is in
     * @author Blake
     */
    public WalkIn(LocalDate date, LocalTime startTime, int numberOfPeople, int tableID, int restaurantID) {
        super(date, startTime, numberOfPeople, tableID, restaurantID);
    }

    @Override
    public String toString() {
        return String.format("""
                             Walk-In
                             %s
                             """, super.toString());
    }
}
