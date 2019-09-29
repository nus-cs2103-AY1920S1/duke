package q.main;

import q.error.FileLoadError;
import q.task.Deadline;
import q.task.Event;
import q.task.Task;
import q.task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 * Manages archiving, saving and loading.
 */
public class Storage {
    private String savePath;
    private String archivePath;

    /**
     * Constructs a storage object.
     *
     * @param savePath    Path to save the list of tasks.
     * @param archivePath Path to the task archive.
     */
    public Storage(String savePath, String archivePath) {
        this.savePath = savePath;
        this.archivePath = archivePath;
    }

    /**
     * Retrieves a list of Tasks from a filepath.
     *
     * @param path String representing the path to get files.
     * @return A List of Tasks from the file.
     * @throws FileLoadError When file cannot be loaded.
     */
    private ArrayList<Task> fromFile(String path) throws FileLoadError {
        File file = new File(path);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String data;
            String[] input;
            while ((data = br.readLine()) != null) {
                input = data.split(" \\| ");
                switch (input[0]) {
                case "T":
                    Todo todo = new Todo(input[2], input[1].equals("1"));
                    tasks.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(input[2], input[1].equals("1"),
                            DateTimeParser.parseFromFile(input[3]));
                    tasks.add(deadline);
                    break;
                case "E":
                    Event event = new Event(input[2], input[1].equals("1"), DateTimeParser.parseFromFile(input[3]));
                    tasks.add(event);
                    break;
                default:
                    throw new FileLoadError();
                }
            }
        } catch (IOException e) {
            throw new FileLoadError();
        }
        return tasks;
    }

    /**
     * Saves a list of tasks.
     *
     * @param tasks List of tasks to be saved.
     */
    public void save(TaskList tasks) {
        Path out = Paths.get(savePath);
        if (Files.notExists(out)) {
            try {
                if (!Files.exists(out.getParent())) {
                    Files.createDirectories(out.getParent());
                }
                Files.createFile(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Files.write(out, tasks.toSaveStringList(), Charset.defaultCharset());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Archives a list of tasks.
     *
     * @param tasks List of tasks to be archived.
     */
    public void archive(TaskList tasks) {
        Path out = Paths.get(archivePath);
        if (Files.notExists(out)) {
            try {
                if (!Files.exists(out.getParent())) {
                    Files.createDirectories(out.getParent());
                }
                Files.createFile(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Files.write(out, tasks.toSaveStringList(), Charset.defaultCharset(), StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tasks.clear();
        save(tasks);
    }

    /**
     * Loads a list of Tasks.
     *
     * @return The loaded list of Tasks.
     * @throws FileLoadError When the list cannot be loaded.
     */
    public ArrayList<Task> load() throws FileLoadError {
        return fromFile(savePath);
    }

    /**
     * Retrieves list of Tasks from the archive.
     *
     * @return The loaded list of Tasks.
     * @throws FileLoadError When the list cannot be loaded.
     */
    public TaskList getArchive() throws FileLoadError {
        return new TaskList(fromFile(archivePath));
    }
}
