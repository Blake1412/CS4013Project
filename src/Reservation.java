import java.time.LocalDate;
import java.time.LocalTime;


public class Reservation {
    private LocalDate dateBooked;
    private LocalTime timeBooked;
    private int numberOfPeople;
    private int restaurantID;
    private String name;
    private String phoneNumber;

    public Reservation(LocalDate DateBooked, LocalTime timeBooked, int numberOfPeople, int restaurantID, String name, String phoneNumber) {
        this.dateBooked = DateBooked;
        this.timeBooked = timeBooked;
        this.numberOfPeople = numberOfPeople;
        this.restaurantID = restaurantID;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(LocalDate dateBooked) {
        this.dateBooked = dateBooked;
    }

    public LocalTime getTimeBooked() {
        return timeBooked;
    }

    public void setTimeBooked(LocalTime timeBooked) {
        this.timeBooked = timeBooked;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
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
}
