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
     * Creates a new Storage which refers to the file of the given filepath.
     * @param filepath used by Duke to store and modify information.
     */
    public Storage(String filepath) {
        this.file = new File(filepath);
    }

    /**
     * Saves the new list to the file.
     * @param content of the modified list.
     * @throws IOException Indicates that the given file to duke cannot be accessed.
     */
    public void saveToFile(String content) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
    }

    /**
     * Loads the saved tasks from the file to Duke's current task list.
     * @return a list representing the tasks.
     * @throws DukeException Indicating that the file given cannot be loaded.
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
            throw new DukeException("Woof Woof!! File cannot be accessed");
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
        String desc = line.substring(7);

        String tag = "";
        boolean hasTags = false;
        Task newTask = new Task("dummy");

        // if there are tags, reprocess string and add them to task
        if (desc.contains("tags")) {
            hasTags = true;
            String[] processed = desc.split("tags:");
            desc = processed[0];
            desc = desc.substring(0, desc.length() - 2).trim();
            tag = processed[1].replace(")", "").trim();
        }

        // Create the appropriate new Task.
        switch (type) {
        case("T"):
            newTask = new ToDo(desc);
            break;
        case("D"):
            String[] dd = desc.split("by:");
            String deadline = dd[1].substring(0, dd[1].length() - 1);
            String description = dd[0].substring(0, dd[0].length() - 1);
            newTask = new Deadline(description, deadline);
            break;
        case("E"):
            String[] edd = desc.split("at:");
            String eventTime = edd[1].substring(0, edd[1].length() - 1);
            String eventDescription = edd[0].substring(0, edd[0].length() - 1);
            newTask = new Event(eventDescription, eventTime);
            break;
        default:
            // Will not enter this as only types T/D/E would be stored in the txt file
        }

        // Read from line to see if task should be marked as done
        String done = "" + detailsArray[4];
        boolean isDone = done.equals("+");

        if (isDone) {
            newTask.quietMarkAsDone();
        }

        // If tags are present, add the whole string of tags. Would have to change this if each tag is an object.
        if (hasTags) {
            newTask.addTag(tag);
        }

        return newTask;
    }
}