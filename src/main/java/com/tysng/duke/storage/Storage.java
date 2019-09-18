package com.tysng.duke.storage;

import com.tysng.duke.domain.Deadline;
import com.tysng.duke.domain.Event;
import com.tysng.duke.domain.Task;
import com.tysng.duke.domain.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class handles saving and loading from local files.
 */
public class Storage {

    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

    /**
     * The platform-independent path to the /data folder.
     */
    private static Path DATA_FOLDER = Path.of(".", "data");

    /**
     * Paths to the storage files.
     */
    private Path storageFilePath = Path.of(DATA_FOLDER.toString(), "duke.txt");
    private Path archiveFilePath = Path.of(DATA_FOLDER.toString(), "archive.txt");

    /**
     * Constructs a new Storage class.  The static factory method will create the data.txt file if the path
     * and the file does not already exist.
     *
     * @return the constructed class
     */
    public static Storage initialize() {
        Storage storage = new Storage();
        try {
            if (!Files.exists(DATA_FOLDER)) {
                Files.createDirectories(DATA_FOLDER);
            }

            if (!Files.exists(storage.storageFilePath)) {
                Files.createFile(storage.storageFilePath);
            }

            if (!Files.exists(storage.archiveFilePath)) {
                Files.createFile(storage.archiveFilePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return storage;
    }

    /**
     * Saves the given task list to local files.
     *
     * @param taskList a list of tasks
     */
    public void saveTasks(List<Task> taskList) {
        saveTasksToFile(this.storageFilePath, taskList);
    }

    /**
     * Saves the given archive list to local files.
     *
     * @param taskList a list of archives
     */
    public void saveArchive(List<Task> taskList) {
        saveTasksToFile(this.archiveFilePath, taskList);
    }

    private void saveTasksToFile(Path path, List<Task> taskList) {
        List<String> entries = taskList.stream().map(this::toFileEntry).collect(Collectors.toList());
        try {
            Files.write(path, entries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the archive from local storage.  When the storage text file is not found, it writes the stack trace
     * to stderr and returns an empty list.
     *
     * @return the loaded archive
     */
    public List<Task> loadArchive() {
        return loadTasksFromFile(this.archiveFilePath);
    }

    /**
     * Loads the tasks from local storage.  When the storage text file is not found, it writes the stack trace
     * to stderr and returns an empty list.
     *
     * @return the loaded tasks
     */
    public List<Task> loadTasks() {
        return loadTasksFromFile(this.storageFilePath);
    }


    private List<Task> loadTasksFromFile(Path path) {
        try {
            List<String> entries = Files.readAllLines(path);
            return entries.stream().map(this::parse).flatMap(Optional::stream).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private String toFileEntry(Task input) {
        if (input instanceof Todo) {
            return String.format("%s | %d | %s",
                    "T",
                    input.isCompleted() ? 1 : 0,
                    input.getTaskName());
        } else if (input instanceof Deadline) {
            return String.format("%s | %d | %s | %s",
                    "D",
                    input.isCompleted() ? 1 : 0,
                    input.getTaskName(),
                    ((Deadline) input).getBy());
        } else if (input instanceof Event) {
            return String.format("%s | %d | %s | %s",
                    "E",
                    input.isCompleted() ? 1 : 0,
                    input.getTaskName(),
                    ((Event) input).getAt());
        }
        return "";
    }

    private Optional<Task> parse(String line) {
        assert !line.isEmpty();
        try {
            List<String> data = Stream.of(line.split("\\|")).map(String::trim).collect(Collectors.toList());
            Task task;
            switch (data.get(0)) {
            case "T":
                task = new Todo(data.get(2));
                break;
            case "D":
                task = new Deadline(data.get(2), DATE_FORMAT.parse(data.get(3)));
                break;
            case "E":
                task = new Event(data.get(2), DATE_FORMAT.parse(data.get(3)));
                break;
            default:
                throw new ParseException(data.get(0) + " is not an acceptable argument", 0);
            }
            task.setCompleted(data.get(1).equals("1"));
            return Optional.of(task);
        } catch (ParseException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


}
