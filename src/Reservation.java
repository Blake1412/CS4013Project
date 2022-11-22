import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation extends Booking {

    private String name;
    private String phoneNumber;

    public Reservation(LocalDate date, LocalTime startTime, int numberOfPeople, int tableID, int restaurantID) {
        super(date, startTime, numberOfPeople, tableID, restaurantID);
    }

    public Reservation(LocalDate date, LocalTime startTime, int numberOfPeople, String name, String phoneNumber, int tableID, int restaurantID, int bookingID) {
        super(date, startTime, numberOfPeople, tableID, restaurantID, bookingID);
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("""
                             Date: %s
                             Time: %s
                             Number of People: %s
                             Name: %s
                             Phone Number: %s
                             Table number: %d
                                                          
                             """, getDate(), getStartTime(), getNumberOfPeople(), getName(), getPhoneNumber(), getTableID());
    }
}
