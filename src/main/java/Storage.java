import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Event;
import duke.task.Todo;

/**
 * Deals with reading and writing tasks to an external file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a storage object.
     *
     * @param filePath Filepath of the external list of tasks.
     */
    public Storage(String filePath) {
        setFilePath(filePath);
    }

    /**
     * Sets the filepath to the input path.
     * @param filePath The path to be used.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the input string to the external file.
     *
     * @param content The list of tasks to be written.
     * @throws IOException If the file doesn't exist.
     */
    public void writeTasks(String content) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(content);
        fw.close();
    }

    /**
     * Loads the list of tasks from the external file.
     *
     * @return An ArrayList of tasks.
     * @throws FileNotFoundException If the filepath is incorrect.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException("Please check your file path!");
        }
        Scanner scanner = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (scanner.hasNext()) {
            try {
                String line = scanner.nextLine();
                tasks.add(parseStoredTask(line));
            } catch (DukeException e) {
                // What to do?
            }
        }
        scanner.close();
        return tasks;
    }

    /**
     * Parses the task description from the external file and creates a corresponding task to represent it.
     *
     * @param task The task description in the external file.
     * @return Corresponding task.
     * @throws DukeException If the task description is incorrect.
     */
    public Task parseStoredTask(String task) throws DukeException {
        String[] tokens = task.substring(3).split("\\s\\|\\s");
        if (tokens[0].equals("D")) {
            Deadline d = new Deadline(tokens[2], tokens[3]);
            if (tokens[1].equals("1")) {
                d.setDone(true);
            }
            return d;
        } else if (tokens[0].equals("E")) {
            Event e = new Event(tokens[2], tokens[3]);
            if (tokens[1].equals("1")) {
                e.setDone(true);
            }
            return e;
        } else if (tokens[0].equals("T")) {
            Todo t = new Todo(tokens[2]);
            if (tokens[1].equals("1")) {
                t.setDone(true);
            }
            return t;
        } else {
            throw new DukeException("OOPS!!! Invalid task.");
        }
    }
}
