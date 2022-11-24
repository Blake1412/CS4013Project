import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * File for any utility methods used throughout the code base
 *
 * @author Blake
 * @author Roger
 */
public class Utils {

    /**
     * Returns whether two given time ranges overlap each other.
     *
     * @param timeOneStart The start of the first time range
     * @param timeOneEnd   The end of the first time range
     * @param timeTwoStart The start of the second time range
     * @param timeTwoEnd   The end of the second time range
     * @return True if the time ranges overlap, otherwise false.
     * @author Blake
     */
    public static boolean timeOverlap(LocalTime timeOneStart, LocalTime timeOneEnd, LocalTime timeTwoStart, LocalTime timeTwoEnd) {
        return timeOneStart.isBefore(timeTwoEnd) && timeTwoStart.isBefore(timeOneEnd);
    }

    /**
     * Reads a csv file and returns a list containing each row of values
     *
     * @param fileName The name of the file to be read
     * @return An ArrayList containing String arrays which contain the values of the lines in the csv file.
     * @author Roger
     */
    public static ArrayList<String[]> fileReader(String fileName) {
        ArrayList<String[]> data = new ArrayList<>();
        String string;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((string = br.readLine()) != null) {
                String[] line = string.split(",");
                data.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("This File does not exist: " + fileName);
        } catch (IOException e) {
            System.out.println("Could not read this file: " + fileName);
        }
        return data;
    }
}
