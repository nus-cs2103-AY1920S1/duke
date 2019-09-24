package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the given file and saving tasks in the same file.
 */
public class Storage {
    /**
     * Default file path used if the user doesn't provide the file name.
     */
    public static final String DEFAULT_STORAGE_FILEPATH = "duke.txt";

    private File file;

    /**
     * Constructs a Storage object given the file path.
     *
     * @param filePath the file path where the tasks are stored.
     *                 It can be either relative or absolute.
     */
    public Storage(String filePath) {
        this.file = new File(System.getProperty("user.dir") + filePath);
    }

    /**
     * Loads the existing tasks from the data file.
     *
     * @return an ArrayList of tasks which are stored as serialized string in the data file
     * @throws DukeException if the file does not exit or the data in the file does not
     *                       follow the correct format (i.e the serialized form of task objects)
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(" \\| ");
                Task task;
                switch (data[0]) {
                case "T":
                    task = new Todo(data[2]);
                    break;
                case "D":
                    task = new Deadline(data[2], data[3]);
                    break;
                case "E":
                    task = new Event(data[2], data[3]);
                    break;
                default:
                    // Empty the existing data file
                    new PrintWriter(file).close();
                    throw new DukeException("Failed to load tasks. Incorrect data format.");
                }
                if (data[1].equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
            scanner.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("Data not found.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Failed to load tasks.");
        }
    }

    /**
     * Converts the current tasks in the list to serialized form and
     * save each task to the destination file. A new file is created if the
     * destination file does not exits.
     *
     * @param tasks the list of tasks to be saved to the destination file
     * @throws DukeException if file writing fails
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            // Empty the existing data file
            new PrintWriter(file).close();
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks.getTasks()) {
                fw.write(task.encode());
                fw.write(Ui.LS);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to save tasks.");
        }
    }
}