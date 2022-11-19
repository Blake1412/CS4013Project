import java.time.LocalTime;

public class Utils {
    public static boolean timeOverlap(LocalTime timeOneStart, LocalTime timeOneEnd, LocalTime timeTwoStart, LocalTime timeTwoEnd) {
        return timeOneStart.isBefore(timeTwoEnd) && timeTwoStart.isBefore(timeOneEnd);
    }
}
