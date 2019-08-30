package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a Storage class that handles all reading and writing into file storage for tasks.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for storage class.
     *
     * @param filePath filePath of the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks in storage file into a List.
     *
     * @return List with Tasks from storage file.
     * @throws DukeException if file is not found.
     */
    public List<Task> load() throws DukeException {
        try {
            List<Task> list = new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                if (!line.isEmpty()) {
                    list.add(Task.taskMaker(line.split(",")));
                }
            }
            return list;
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Method to delete a line from the file.
     *
     * @param toDelete Line to be deleted.
     * @throws DukeException if file is not found.
     */
    public void deleteLine(String toDelete) throws DukeException {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            List<String> newlines = new ArrayList<>();
            for (String line : lines) {
                if (!line.equals(toDelete) && !line.contains(toDelete)) {
                    newlines.add(line);
                }
            }
            Files.write(Paths.get(filePath), newlines);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Method to replace a line in the storage file.
     *
     * @param before line to be replaced.
     * @param after  line to replace with.
     * @throws DukeException if file is not found.
     */
    public void replaceLine(String before, String after) throws DukeException {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            List<String> newlines = new ArrayList<>();
            for (String line : lines) {
                if (line.equals(before) || line.contains(before)) {
                    newlines.add(after);
                } else {
                    newlines.add(line);
                }
            }
            Files.write(Paths.get(filePath), newlines);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Method to store a new task into the storage file.
     *
     * @param t Task to be written into the file.
     * @throws DukeException if file is not found.
     */
    public void addTask(Task t) throws DukeException {
        try {
            Files.write(Paths.get(filePath), Collections.singletonList(t.storageString()), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
