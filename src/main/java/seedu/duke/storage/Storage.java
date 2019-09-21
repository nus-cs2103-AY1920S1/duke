package seedu.duke.storage;

import seedu.duke.exceptions.DukeException;
import seedu.duke.trackables.Deadline;
import seedu.duke.trackables.Event;
import seedu.duke.trackables.Task;
import seedu.duke.trackables.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a Storage system that handles saving and loading of tasks to and from the file-system.
 * Storage is a Singleton to prevent concurrent access to the storage file due to multiple instantiation. The Storage
 * {@code instance} is instantiated exactly once.
 */
public class Storage {

    /**
     * Default file path used.
     */
    private static final String DEFAULT_STORAGE_PATH = "/data/tasksfile.txt";

    /**
     * Singleton Storage Instance.
     */
    private static Storage instance;

    private final Path path;

    private Storage() {
        path = Paths.get(System.getProperty("user.dir") + DEFAULT_STORAGE_PATH);
    }

    /**
     * Gets the Singleton instance of the Storage Object.
     *
     * @return the Storage instance.
     */
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    /**
     * Saves the list of tasks to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void saveToDisk(TaskList tasks) throws StorageOperationException {
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            assert (Files.exists(path));        // File should exist at this point.
            List<String> tasksAsString = extractStringsFromTasks(tasks);
            Files.write(path, tasksAsString);
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path, ioe);
        }
    }

    /**
     * Loads the Tasks data from the storage file, and then returns it.
     *
     * @return The populated {@code TaskList}, otherwise an empty {@code TaskList} if the file does not exist,
     * or is an irregular file.
     * @throws StorageOperationException thrown when there were errors reading and/or converting data from file.
     */
    public TaskList loadFromDisk() throws StorageOperationException {

        if (!Files.exists(path)) {
            return new TaskList();
        }

        try {
            return new TaskList(restoreTasksFromStrings(Files.readAllLines(path)));
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path, ioe);
        } catch (Exception e) {
            throw new StorageOperationException("Fatal error occurred. Could not load tasks.", e);
        }
    }

    /**
     * Converts all the {@code tasks} as their equivalent Strings.
     *
     * @param tasks Lists containing all the tasks to convert.
     * @return A list of Tasks in their String equivalent form otherwise, an empty {@code List<String>}
     *         if {@code tasks} is empty.
     */
    private List<String> extractStringsFromTasks(TaskList tasks) {
        return tasks.getList().stream().map(Task::getAsString).collect(Collectors.toList());
    }

    /**
     * Converts all the {@code stringTasks} as their equivalent Task Objects.
     *
     * @param stringTasks List containing all tasks in their string forms.
     * @return A list of Tasks otherwise, an empty {@code List<Task} if {@code stringTasks} is empty.
     */
    private List<Task> restoreTasksFromStrings(List<String> stringTasks) {
        return stringTasks.stream()
            .map(identifyTask -> identifyTask.split(" \\| "))        // Split String
            .map(this::stringArgsToTask).collect(Collectors.toList());     // Run each String[] through stringToTask()
    }

    /**
     * Converts a single set of String Arguments into their respective Task subclass.
     *
     * @param args Contains the task data.
     * @return Returns the populated task subclass.
     */
    private Task stringArgsToTask(String... args) {
        switch (args[0]) {
        case "T":
            return new Todo(args);
        case "E":
            return new Event(args);
        case "D":
            return new Deadline(args);
        default:
            return new Task(args);
        }
    }

    /**
     * Signals that some error has occurred while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends DukeException {

        public StorageOperationException() {
            super();
        }

        public StorageOperationException(String message) {
            super(message);
        }

        public StorageOperationException(String message, Throwable cause) {
            super(message, cause);
        }
    }


}
