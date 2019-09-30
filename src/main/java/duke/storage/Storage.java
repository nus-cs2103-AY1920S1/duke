package duke.storage;

import duke.exception.DukeStorageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static duke.task.Task.DATE_TIME_FORMATTER;
import static duke.ui.Messages.LOAD_TASK_FILE_CORRUPTED;
import static duke.ui.Messages.WRITE_TASK_FAILED;

/**
 * The storage interface of the Duke application.
 */
public class Storage {
    private static final String DATA_FILE_SEPARATOR = " | ";
    private static final Map<Class<? extends Task>, String> TASK_SHORT_NAME_MAP = Map.of(
        Todo.class, "T",
        Event.class, "E",
        Deadline.class, "D"
    );
    private static final Map<Boolean, String> TASK_STATUS_MAP = Map.of(
        true, "1",
        false, "0"
    );
    private final File file;

    /**
     * Constructs a Storage object to save tasks to a data file.
     *
     * @param fileName the filename of the date file to save tasks
     */
    public Storage(final String fileName) {
        Path dataDir = getDataDir();
        this.file = dataDir.resolve(fileName).toFile();
        if (!dataDir.toFile().exists()) {
            dataDir.toFile().mkdirs();
        }
    }

    /**
     * Loads tasks from the data file.
     *
     * @param tasks the TaskList to load tasks into
     * @throws DukeStorageException if an parsing error occurs
     */
    public void loadTasks(TaskList tasks) throws DukeStorageException {
        Scanner scanner;
        try {
            scanner = new Scanner(this.file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return;
        }
        assert this.file.exists(); // file should exist if Scanner can be created

        DukeStorageException fileCorruptedException = new DukeStorageException(LOAD_TASK_FILE_CORRUPTED);
        try {
            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(" \\| ");
                String type = tokens[0];
                boolean isDone = !tokens[1].equals("0");
                String description = tokens[2];

                Task task;
                switch (type) {
                    case "T": {
                        task = new Todo(description, isDone);
                        break;
                    }
                    case "D": {
                        LocalDateTime by = LocalDateTime.parse(tokens[3], DATE_TIME_FORMATTER);
                        task = new Deadline(description, by, isDone);
                        break;
                    }
                    case "E": {
                        LocalDateTime at = LocalDateTime.parse(tokens[3], DATE_TIME_FORMATTER);
                        task = new Event(description, at, isDone);
                        break;
                    }
                    default:
                        throw fileCorruptedException;
                }
                tasks.addTask(task);
            }
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw fileCorruptedException;
        } finally {
            scanner.close();
        }
    }

    /**
     * Writes tasks to data file.
     *
     * @param tasks The tasks to write to the data file
     * @throws DukeStorageException if the data file cannot be written
     */
    public void writeTasks(final TaskList tasks) throws DukeStorageException {
        assert this.file.getParentFile().exists(); // parent directory should have been created in the constructor
        try (FileWriter writer = new FileWriter(this.file, StandardCharsets.UTF_8, false)) {
            assert this.file.exists(); // file should be created when FileWriter is created
            writer.write(serializeTaskList(tasks));
        } catch (IOException e) {
            throw new DukeStorageException(WRITE_TASK_FAILED + e.getMessage());
        }
    }

    /**
     * Returns the directory that Duke should store its data.
     *
     * @return the directory that Duke should store its data
     */
    private Path getDataDir() {
        String os = System.getProperty("os.name").toUpperCase();
        String path;
        if (os.contains("WIN")) {
            path = System.getenv("APPDATA");
        } else if (os.contains("MAC")) {
            path = System.getProperty("user.home")
                + "/Library/Application Support";
        } else { // Assume *nix
            String xdgDataHome = System.getenv("XDG_DATA_HOME");
            path = xdgDataHome == null
                ? System.getProperty("user.home") + "/.local/share"
                : xdgDataHome;
        }
        return Path.of(path + "/Duke");
    }

    /**
     * Returns the representation of the given task in the data file.
     *
     * @param task the task to serialize
     * @return the representation of the given task in the data file
     */
    private String serializeTask(final Task task) {
        List<String> tokens = new ArrayList<>(List.of(
            TASK_SHORT_NAME_MAP.get(task.getClass()),
            TASK_STATUS_MAP.get(task.isDone()),
            task.getDescription()
        ));
        if (task instanceof Event) {
            tokens.add(((Event) task).getAtString());
        } else if (task instanceof Deadline) {
            tokens.add(((Deadline) task).getByString());
        }
        return String.join(DATA_FILE_SEPARATOR, tokens);
    }

    /**
     * Returns the representation of the given TaskList in the data file.
     *
     * @param tasks the TaskList to serialize
     * @return the representation of the given TaskList in the data file
     */
    private String serializeTaskList(final TaskList tasks) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            ret.append(serializeTask(tasks.getTask(i)))
                .append(System.lineSeparator());
        }
        return ret.toString();
    }
}
