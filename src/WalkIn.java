import java.time.LocalDate;
import java.time.LocalTime;

public class WalkIn extends Booking {

    public WalkIn(LocalDate date, LocalTime startTime, int numberOfPeople, int tableID, int restaurantID) {
        super(date, startTime, numberOfPeople, tableID, restaurantID);
    }

    public WalkIn(LocalDate date, LocalTime startTime, int numberOfPeople, int tableID, int restaurantID, int bookingID) {
        super(date, startTime, numberOfPeople, tableID, restaurantID, bookingID);
    }

    @Override
    public String toString() {
        return String.format("""
                             Walk-In
                             %s
                             """, super.toString());
    }
}
