import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class deals with retrieving tasks from the local file
 * and writing back to the file in the case of any modifications to the tasks.
 */
public class Storage {
    
    private String filePath;

    /**
     * Constructs a new Storage object which obtains its data for initialisation
     * from a specified file.  
     * 
     * @param filePath File path of the file wherein tasks are stored. 
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a new Task object created from an individual line of description
     * of the task.
     * 
     * @param line The string which, in a single line, describes a particular task.
     * @return The task constructed from the inputted task detail line.
     * @throws DukeException When the line of string being read does not describe any particular type of task.
     */
    public static Task createTaskFromFile(String line) throws DukeException {
        String[] command = line.split(" \\| ");
        boolean isPending = command[1].equals("1") ? true : false;
        switch (command[0]) {
        case "T":
            return new ToDos(command[2], isPending);
        case "D":
            return new Deadline(command[2], command[3], isPending);
        case "E":
            return new Event(command[2], command[3], isPending);
        default:
            throw new DukeException("Uncategorizable task.");
        }
    }

    /**
     * Writes the inputted task back into the file at the bottom of the file.
     * 
     * @param task The task being stored in the file.
     * @throws IOException When the file being written to cannot be found.
     */
    public void addTaskToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(task.toStringForFile() + System.lineSeparator());
        fw.close();
    }

    /**
     * Returns an array list of tasks constructed from the tasks saved locally on
     * the file.
     * 
     * @return An array list of tasks.
     * @throws FileNotFoundException When the file being written to cannot be found.
     * @throws DukeException When a particular task saved in the file is of the 
     *     wrong format or does not describe an existing type of task.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(this.filePath);
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                list.add(createTaskFromFile(scan.nextLine()));
            }
            scan.close();
        } catch (FileNotFoundException f) {
            createFile(file);
        }
        return list;
    }

    private void createFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Writes all the tasks from an array list back into the file on the local system.
     * 
     * @param filePath Location of the file where the tasks are to be stored.
     * @param list The array list containing the tasks to be written back onto the file.
     * @throws IOException When the file being written to be cannot be found.
     */
    public void writeToFile(String filePath, ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : list) {
            fw.write(t.toStringForFile() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Returns the file path of the file which the storage object 
     * is using for read and write purposes.
     * 
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }
}