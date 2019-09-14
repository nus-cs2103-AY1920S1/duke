import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class Storage {
    String filePath;

    /**
     * Constructs Storage object to read anf write to storage file given.
     *
     * @param filePath the file path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the storage file and loads the TaskList.
     *
     * @return the TaskList after reading storage file.
     */
    public ArrayList<Task> load() {
        try {
            File dataFile = createOrRetrieve(filePath);
            ArrayList<String> stringOfTasks = readFile(dataFile);
            ArrayList<Task> arrOfTasks = new ArrayList<>();
            for (String s : stringOfTasks) {
                Task t = Task.genTaskFromData(s);
                arrOfTasks.add(t);
            }
            return arrOfTasks;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates the storage file if does not exist. Retrieves file otherwise.
     *
     * @param filePath the path of the storage file.
     * @return the File Object of the storage file.
     * @throws IOException if cannot be created.
     */
    private static File createOrRetrieve(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
        }
        return file;
    }

    /**
     * Reads the data that is stored in the given storage file.
     *
     * @param file the File object of the storage file.
     * @return  ArrayList of Task objects after reading from data file.
     */
    private static ArrayList<String> readFile(File file) {
        ArrayList<String> stringOfTasks = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                stringOfTasks.add(line);
            }
            reader.close();
            return stringOfTasks;
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", file);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Saves and writes all Task from TaskArrayList into storage file in the required data format.
     *
     * @param taskArrayList the list of Tasks from duke
     */
    public void save(ArrayList<Task> taskArrayList) {
        File file = new File("./data/duke.txt");
        FileWriter writer;
        try {
            writer = new FileWriter(file);
            PrintWriter printer = new PrintWriter(writer);
            for (Task t : taskArrayList) {
                printer.append(t.toDataFormat() + "\n");
            }
            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
