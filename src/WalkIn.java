import java.time.LocalDate;
import java.time.LocalTime;

public class WalkIn extends Booking {
    public WalkIn(LocalDate date, LocalTime startTime, int numberOfPeople, int restaurantID, int bookingID) {
        super(date, startTime, numberOfPeople, restaurantID, bookingID);
    }

    @Override
    public String toString() {
        return String.format("""
                             Walk-In
                             %s
                             """, super.toString());
    }
}
