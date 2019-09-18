package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Represents a storage object which will read and write files into specific file path.
 */
public class Storage {
    private ArrayList<Task> tasks = new ArrayList<>();
    private String filePath;

    /**
     * Constructor for storage that reads and writes to data file.
     *
     * @param filePath where the data file is stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads data from filepath and creates an array list of duke.task.Task.
     *
     * @return an arraylist of duke.task.Task from read data
     * @throws IOException file is not found
     */
    public ArrayList<Task> load() throws IOException {
        File file = new File(filePath);
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        while ((line = in.readLine()) != null) {
            String regex = " \\| ";
            String[] data = line.split(regex);
            Task t;
            switch (data[0]) {
            case "T":
                t = new Todo(data[2], changeStringToBoolean(data[1]));
                break;
            case "D":
                t = new Deadline(data[2], data[3], changeStringToBoolean(data[1]));
                break;
            case "E":
                t = new Event(data[2], data[3], changeStringToBoolean(data[1]));
                break;
            default:
                t = null;
            }
            assert t != null;
            tasks.add(t);
        }
        in.close();
        return tasks;
    }

    /**
     * Converts number into boolean. 1 for true and 0 for false.
     *
     * @param number number to be determined
     * @return true or false
     */
    private boolean changeStringToBoolean(String number) {
        return (number.equals("1"));
    }

    /**
     * Writes the data into filepath.
     */
    public void rewriteData() {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : tasks) {
                fw.write(t.toDataString() + "\n");
            }
            System.out.println("Saving data to: " + filePath);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
