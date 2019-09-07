package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
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
     * Reads the storage file and returns a string for implementation of history.
     * @return String.
     * @throws IOException thrown when IO error occurs.
     */
    public String readFile() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (String line : lines) {
            if (!line.isEmpty()) {
                stringBuilder.append(line).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Saves the string into the storage file for implementation of history.
     * @param storageString String
     * @throws IOException thrown when IO error occurs.
     */
    public void save(String storageString) throws IOException {
        String[] lines = storageString.split("\n");
        List<String> newlines = new ArrayList<>(Arrays.asList(lines));
        Files.write(Paths.get(filePath), newlines);
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
        assert !toDelete.isEmpty() : "String to be deleted should not be empty.";
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
            assert !before.isEmpty() : "String to be overwritten should not be empty.";
            assert !after.isEmpty() : "String to write should not be empty.";
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
