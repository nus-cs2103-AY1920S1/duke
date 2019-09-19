import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/** Class to manage storage of tasks from/to file. */
class Storage {

    // path of storage file
    Path path;
    // date formatting is a constant
    static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * Constructor for the object.
     * @param path Path to the storage file.
     */
    public Storage(String path) {
        this.path = Paths.get(path);
        // create file if not present
        if (!Files.exists(this.path)) {
            try {
                Files.createFile(this.path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Function to read tasks from the storage file.
     * @return A list of read tasks.
     * @throws DukeException Exception in case cannot read tasks.
     */
    public ArrayList<Task> readTasksFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            List<String> taskStrings = Files.readAllLines(this.path);
            for (String task : taskStrings) {
                tasks.add(readTask(task));
            }
        } catch (IOException e) {
            throw new DukeException("Was not able to read the file. :(");
        }

        return tasks;
    }

    /**
     * Function to write tasks to the storage file.
     * @param tl TaskList representing the list of tasks.
     * @throws DukeException Exception in case cannot write tasks.
     */
    public void writeTasksToFile(TaskList tl) throws DukeException {
        try {
            BufferedWriter writer = Files.newBufferedWriter(this.path, Charset.forName("UTF-8"));
            for (Task task : tl.getTasks()) {
                writer.write(writeTask(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Was not able to write to the file. :(");
        }
    }

    /**
     * Static method to read task from a string.
     * @param task String representing the task.
     * @return The parsed task.
     * @throws DukeException Exception in case cannot read task.
     */
    public static Task readTask(String task) throws DukeException {
        String[] taskParams = task.split(" - ");
        Task returnTask = null;
        switch (taskParams[0]) {
        case "T":
            ToDo todo = new ToDo(taskParams[2]);
            if (Integer.parseInt(taskParams[1]) == 1) {
                todo.markDone();
            }
            returnTask = todo;
            break;
        case "E":
            try {
                Event event = new Event(taskParams[2], format.parse(taskParams[3]));
                if (Integer.parseInt(taskParams[1]) == 1) {
                    event.markDone();
                }
                returnTask = event;
                break;
            } catch (ParseException e) {
                throw new DukeException("Incorrect date format given. :(");
            }
        case "D":
            try {
                Deadline dl = new Deadline(taskParams[2], format.parse(taskParams[3]));
                if (Integer.parseInt(taskParams[1]) == 1) {
                    dl.markDone();
                }
                returnTask = dl;
                break;
            } catch (ParseException e) {
                throw new DukeException("Incorrect date format given. :(");
            }
        default:
            break;
        }
        return returnTask;
    }

    /**
     * Static method to generate a string representing the task.
     * @param task The Task to convert to String.
     * @return String representing the task.
     */
    public static String writeTask(Task task) {
        String taskStr = "";
        String doneStr = task.isTaskDone() ? "1" : "0";
        switch (task.getType()) {
        case TODO:
            taskStr = String.format("T - %s - %s", doneStr, task.getName());
            break;
        case EVENT:
            taskStr = String.format("E - %s - %s - %s", doneStr, task.getName(), format.format(task.getDate()));
            break;
        case DEADLINE:
            taskStr = String.format("D - %s - %s - %s", doneStr, task.getName(), format.format(task.getDate()));
            break;
        default:
            break;
        }
        return taskStr;
    }
}
