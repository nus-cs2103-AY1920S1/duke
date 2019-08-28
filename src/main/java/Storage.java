import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles all input and output related to the file associated with Duke.
 */
public class Storage {
    private File file;

    /**
     * Creates a new Storage which refers to the file of the given filepath
     * @param filepath used by Duke to store and modify information.
     */
    public Storage(String filepath) {
        this.file = new File(filepath);
    }

    /**
     * Saves the new list to the file.
     * @param content of the modified list.
     * @throws IOException
     */
    public void saveToFile(String content) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
    }

    /**
     * Loads the saved tasks from the file to Duke's current task list.
     * @return a list representing the tasks.
     * @throws DukeException
     */
    public ArrayList<Task> loadPreviousTasks() throws DukeException {
        ArrayList<Task> loadList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = "";
            while ((str = br.readLine()) != null) {
                Task task = txtProcessor(str);
                loadList.add(task);
            }
        } catch (IOException e) {
            throw new DukeException("File cannot be accessed");
        }

        return loadList;
    }

    /**
     * Processes a line of text from the file referred to by Storage.
     * @param line from the text file.
     * @return the Task generated from this line.
     */
    private Task txtProcessor(String line) {
        String details = line.substring(0,6);
        char[] detailsArray = details.toCharArray();
        String type = "" + detailsArray[1];
        String done = "" + detailsArray[4];
        boolean isDone = done.equals("+");
        String desc = line.substring(7);

        Task newTask = new Task("dummy");

        if (type.equals("T")) {
            newTask = new ToDo(desc);
        }

        if (type.equals("D")) {
            String[] dd = desc.split("by:");
            String deadline = dd[1].substring(0, dd[1].length() - 1);
            String description = dd[0].substring(0, dd[0].length() - 1);
            newTask = new Deadline(description, deadline);
        }

        if (type.equals("E")) {
            String[] dd = desc.split("at:");
            String deadline = dd[1].substring(0, dd[1].length() - 1);
            String description = dd[0].substring(0, dd[0].length() - 1);
            newTask = new Deadline(description, deadline);
        }

        if (isDone) {
            newTask.quietMarkAsDone();
        }

        return newTask;
    }
}