package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;

    /**
     * Constructs an object that handles file save and load using given the file path.
     *
     * @param path the file path to the task list file.
     */
    public Storage(String path) {
        this.filePath = path;
        file = new File(path);
    }

    /**
     * Loads the task list from the file.
     *
     * @return the content of the task list file in raw string.
     * @throws FileNotFoundException if the file does not exist.
     */
    public String load() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        StringBuilder myBuilder = new StringBuilder();
        while (fileScanner.hasNext()) {
            myBuilder.append(fileScanner.nextLine() + "\n");
        }
        return myBuilder.toString();
    }

    /**
     * Saves the task list to the file.
     *
     * @param tasks the task list given to be saved to the file.
     * @throws DukeException if the task list cannot be saved to the file.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(filePath);
            for (Task t : tasks.getTaskList()) {
                writer.write(t.toExportFormat() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
