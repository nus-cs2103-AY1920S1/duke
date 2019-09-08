package duke.storage;

import duke.exception.DukeException;
import duke.exception.IoDukeException;
import duke.task.Task;
import duke.task.TaskList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages storage information for the program.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads a list of tasks from the file path in the constructor object.
     *
     * @return List of tasks.
     * @throws DukeException If the file could not be read.
     */
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");

                Task task = StorageSerializer.deserialize(tokens);
                tasks.add(task);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            throw new IoDukeException("Error opening task file for reading");
        } catch (IOException e) {
            throw new IoDukeException("Error closing file reader");
        } catch (ParseException e) {
            throw new IoDukeException("Error parsing date in task file");
        }

        return tasks;
    }

    /**
     * Saves a list of tasks to the file path in the constructor object.
     *
     * @throws DukeException If the file failed to save.
     */
    public void save(TaskList tasks) throws DukeException {
        PrintWriter writer = null;
        try {
            Path path = Paths.get(filePath);

            // Create directories if necessary
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent().getFileName());
            }

            writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
            for (Task task : tasks.getTaskList()) {
                String output = StorageSerializer.serialize(task);
                writer.write(output);
            }
        } catch (IOException e) {
            throw new IoDukeException("Error opening file for saving");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
