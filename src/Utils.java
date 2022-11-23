import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

public class Utils {
    public static boolean timeOverlap(LocalTime timeOneStart, LocalTime timeOneEnd, LocalTime timeTwoStart, LocalTime timeTwoEnd) {
        return timeOneStart.isBefore(timeTwoEnd) && timeTwoStart.isBefore(timeOneEnd);
    }

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
