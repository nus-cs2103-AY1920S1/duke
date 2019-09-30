package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private List<Task> tasks = new ArrayList<Task>();
    private String filePath;
    private File file;

    /**
     * Creates a new Storage instance with the given path to the file.
     *
     * @param path path to the file.
     */
    public Storage(String path) {
        this.filePath = path;
        this.file = new File(filePath);
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
     * @throws DukeException exception produced by reading empty file.
     */
    public List<Task> load() throws DukeException {
        try {
            if (file.createNewFile()) System.out.println("File created");
            // Create a Scanner using the File as the source
            Scanner s = new Scanner(file);
            if (!s.hasNext()) throw new DukeException("Your task list is currently empty.");
            while (s.hasNext()) {
                readTasks(s.nextLine());
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

    }

    /**
     * Reads task info and generates new <code>Task</code> instance.
     *
     * @param str String containing information about a task.
     */
    private void readTasks(String str) throws DukeException {
        try {
            String[] arr = str.split(" \\| ");
            String taskType = arr[0];
            int taskStatus = Integer.parseInt(arr[1]);
            String taskDes = arr[2];
            Task task;
            switch (taskType) {
            case "T":
                task = new Todo(taskDes);
                tasks.add(task);
                if (taskStatus == 1) task.mark();
                break;
            case "D":
                task = new Deadline(taskDes, new SimpleDateFormat("d/MM/yyyy HHmm").parse(arr[3]));
                tasks.add(task);
                if (taskStatus == 1) task.mark();
                break;
            case "E":
                task = new Event(taskDes, new SimpleDateFormat("d/MM/yyyy HHmm").parse(arr[3]));
                tasks.add(task);
                if (taskStatus == 1) task.mark();
                break;
            default:
                throw new AssertionError(taskType);
            }
        } catch (ParseException e) {
            throw new DukeException("Error when reading data from file");
        }
    }

}
