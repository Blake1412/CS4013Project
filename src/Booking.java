import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Booking {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int numberOfPeople;
    private int tableID;
    private int restaurantID;
    private int bookingID;

    protected Booking(LocalDate date, LocalTime startTime, int numberOfPeople, int tableID, int restaurantID) {
        this.date = date;
        this.startTime = startTime;
        endTime = startTime.plusHours(2);
        this.numberOfPeople = numberOfPeople;
        this.tableID = tableID;
        this.restaurantID = restaurantID;
    }

    protected Booking(LocalDate date, LocalTime startTime, int numberOfPeople, int tableID, int restaurantID, int bookingID) {
        this(date, startTime, numberOfPeople, tableID, restaurantID);
        this.tableID = tableID;
        this.restaurantID = restaurantID;
        this.bookingID = bookingID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
        this.endTime = startTime.plusHours(2);
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    @Override
    public String toString() {
        return String.format("""
                             Reservation
                             Date: %s
                             Time: %s
                             Number of People: %d
                             Table number: %d
                             
                             """, date, startTime, numberOfPeople, tableID);
    }
}
