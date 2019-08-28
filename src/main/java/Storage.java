import java.io.BufferedWriter;
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

    public Storage(String path) {
        this.path = Paths.get(path);
    }

    // function to read tasks from a file
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

    // function to write tasks to a file
    public void writeTasksToFile(TaskList tl) throws DukeException {
        try {
            Path path = Paths.get("data/duke.txt");
            BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"));
            for (Task task : tl.getTasks()) {
                writer.write(writeTask(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Was not able to write to the file. :(");
        }
    }

    // function to read tasks from a single line
    private static Task readTask(String task) throws DukeException {
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

    // generate string to represent task
    private static String writeTask(Task task) {
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
