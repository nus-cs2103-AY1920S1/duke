import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filepath;

    Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * loads a list from the initiated filepath
     * @return formatted and read list
     */
    ArrayList<ListItem> load() {
        try {
            Scanner reader = new Scanner(new FileReader(filepath));
            ArrayList<ListItem> tempArray = new ArrayList<>();
            while(reader.hasNext()) {
                String commandIn = reader.nextLine();
                String[] commandArray = commandIn.split("@");
                tempArray.add(new ListItem(commandArray[2], commandArray[1], commandArray[3]));
                if (commandArray[0].equals("true")) {
                    tempArray.get((tempArray.size() - 1)).done();
                }
            }
            ArrayList toReturn = new ArrayList();
            toReturn.addAll(tempArray);
            return toReturn;
        }
        catch (IOException e) {
            System.out.println("ERROR cannot read");
        }
        return new ArrayList<>();
    }

    /**
     * Saves data to associated filepath
     * @param toSave List that you want to save
     */
    void save(ArrayList<ListItem> toSave) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            for (ListItem item : toSave) {
                writer.write(item.format());
            }
            writer.flush();
        }
        catch (IOException e) {
            System.out.println("ERROR Cannot Save");
        }
    }


}
