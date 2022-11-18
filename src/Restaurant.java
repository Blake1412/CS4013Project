import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {
    private int ID;
    private final ArrayList<Table> tables = new ArrayList<>();
    private FileWriter writer;

    private ArrayList<Menu> menu = new ArrayList<>();

    public Restaurant(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public void addTable(int ID, int capacity) {
        tables.add(new Table(ID, capacity));

        try {
            writer = new FileWriter("Tables.csv", true);
            writer.write(String.format("%d,%d,%d\n", getID(), ID, capacity));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeTable(int ID) {
        tables.removeIf(table -> table.getID() == ID);

        try {
            String table = String.format("%d,%d", getID(), ID);
            Path tables = Path.of("Tables.csv");
            Scanner scanner = new Scanner(tables);
            writer = new FileWriter("temp.csv");
            while (scanner.hasNext()) {
                String nl = scanner.nextLine();
                if (nl.equals("")) continue;
                if (!nl.substring(0, nl.lastIndexOf(",")).equals(table)) writer.write(nl + "\n");
            }
            scanner.close();
            writer.close();
            Path temp = Path.of("temp.csv");
            Files.copy(temp, tables, StandardCopyOption.REPLACE_EXISTING);
            Files.delete(temp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Menu> getMenu(){
        return menu;
    }

    public void addMenu (int ID, String category, String item, int price ){
        menu.add(new Menu(ID, category, item, price));

    }
}