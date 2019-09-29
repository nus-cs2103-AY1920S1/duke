import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the read and write of data into a text file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor to specify the file path of the text file used
     * for saving and loading of Tasks for the list.
     *
     * @param filePath directory of text file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a ArrayList of Task type for Duke to use as an initial list.
     * Data is obtained from a text file with a specific format.
     *
     * @return List of Tasks to be used
     * @throws DukeException When format is wrong and filePath is invalid
     */
    public ArrayList<Task> loadData() throws DukeException {
        try {
            /*
            // Code to load example list.
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(filePath);
             */
            File f = new File(filePath);
            ArrayList<Task> list = new ArrayList<>();
            //System.out.println(f.getAbsolutePath());
            Scanner sc = new Scanner(f);

            while (sc.hasNext()) {
                String type = sc.next();
                switch (type) {
                case "T":
                    boolean done = sc.nextBoolean();
                    String taskName = sc.nextLine().trim();
                    list.add(new Task(taskName, done));
                    break;
                case "D":
                    done = sc.nextBoolean();
                    taskName = sc.nextLine().trim();
                    String[] userWords = taskName.split("/");
                    list.add(new Deadline(userWords[0], userWords[1], done));
                    break;
                case "E":
                    done = sc.nextBoolean();
                    taskName = sc.nextLine().trim();
                    userWords = taskName.split("/");
                    list.add(new Event(userWords[0], userWords[1], done));
                    break;
                default:
                    throw new DukeException("Wrong type used inside of save file");
                }
            }
            return list;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found. Generating empty list...");
        }
    }

    /**
     * Saves all of the Tasks inside the list into a text file for future usage.
     * It will throw an exception if the file path was not specified in storage upon initialisation.
     * However, an IOException is thrown instead of DukeException to be able
     * to exit the program despite not saving.
     *
     * @param list List of Tasks used in Duke
     */
    public void saveData(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter(filePath);
            String data = "";
            for (Task t : list) {
                data += t.writeFormat() + "\n";
            }
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to save data. File path may not have been specified.");
        }
    }
}
