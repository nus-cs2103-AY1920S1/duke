import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private List<Task> tasks = new ArrayList<Task>();
    private String filePath;

    /**
     * Creates a new Storage instance with the given path to the file.
     *
     * @param path path to the file.
     */
    public Storage(String path) {
        this.filePath = path;
    }

    /**
     * Creates a new FileWriter for the given file path of the file and
     * writes text into the file.
     *
     * @param text text to be written into the file.
     * @throws IOException exception produced by failed or interrupted I/O operations.
     */
    public void writeToFile(String text) throws IOException {
        // Create a FileWriter for the given file path
        FileWriter fw = new FileWriter(filePath);
        fw.write(text);
        fw.close();
    }

    /**
     * Loads file from the file path and translates the lines into <code>Task</code> objects
     * to be stored into a list.
     *
     * @return a list of Tasks.
     * @throws FileNotFoundException exception produced by failure of finding a file.
     * @throws DukeException exception produced by reading empty file.
     */
    public List<Task> load() throws FileNotFoundException, DukeException {
        // Create a File for the given file path
        File f = new File(filePath);
        // Create a Scanner using the File as the source
        Scanner s = new Scanner(f);
        if (!s.hasNext()) throw new DukeException(
                "Your task list is currently empty.");
        while (s.hasNext()) {
            String str = s.nextLine();
            String[] arr = str.split(" \\| ");
            String taskType = arr[0];
            int taskStatus = Integer.valueOf(arr[1]);
            String taskDes = arr[2];
            String taskTime = "";
            Task task;
            if (taskType.equals("T")) {
                task = new ToDo(taskDes);
                tasks.add(task);
                if (taskStatus == 1) task.mark();
            } else if (taskType.equals("D")) {
                taskTime = arr[3];
                task = new Deadline(taskDes, taskTime);
                tasks.add(task);
                if (taskStatus == 1) task.mark();
            } else if (taskType.equals("E")) {
                taskTime = arr[3];
                task = new Event(taskDes, taskTime);
                tasks.add(task);
                if (taskStatus == 1) task.mark();
            }
        }
        return tasks;
    }

}
