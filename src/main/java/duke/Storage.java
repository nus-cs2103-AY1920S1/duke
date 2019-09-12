package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private File mainFile;
    private File archivedFile;

    /**
     * Constructs an object that handles file save and load using given the file path.
     *
     * @param path the file path to the task list file.
     */
    public Storage(String path) {
        mainFile = new File(path);
        String archivedFilePath = path.substring(0, path.indexOf("/")) + "/archived.txt";
        archivedFile = new File(archivedFilePath);
    }

    /**
     * Loads the task list from a specific file.
     *
     * @return the content of the task list file in raw string.
     * @throws FileNotFoundException if the file does not exist.
     */
    private String load(File file) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        StringBuilder myBuilder = new StringBuilder();
        while (fileScanner.hasNext()) {
            myBuilder.append(fileScanner.nextLine() + "\n");
        }
        return myBuilder.toString();
    }

    /**
     * Loads the task list from the main file.
     *
     * @return the content of the task list file in raw string.
     * @throws FileNotFoundException if the file does not exist.
     */
    public String loadMain() throws FileNotFoundException {
        return load(mainFile);
    }

    /**
     * Loads the task list from the archive file.
     *
     * @return the content of the task list file in raw string.
     * @throws FileNotFoundException if the file does not exist.
     */
    public String loadArchive() throws FileNotFoundException {
        return load(archivedFile);
    }

    /**
     * Saves the task list to a specific file.
     *
     * @param tasks the task list given to be saved to the file.
     * @param file the file object to store the task list.
     * @throws DukeException if the task list cannot be saved to the file.
     */
    private void save(TaskList tasks, File file) throws DukeException {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file.getPath());
            for (Task t : tasks.getTaskList()) {
                writer.write(t.toExportFormat() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Save the task list to the main file.
     *
     * @param tasks the task list given to be saved to the file.
     * @throws DukeException if the task list cannot be saved to the file.
     */
    public void saveMain(TaskList tasks) throws DukeException {
        this.save(tasks, mainFile);
    }

    /**
     * Save the task list to the archived file.
     *
     * @param archivedTasks the archived task list to be saved to the file.
     * @throws DukeException if the task list cannot be saved to the file.
     */
    public void saveArchive(TaskList archivedTasks) throws DukeException {
        this.save(archivedTasks, archivedFile);
    }
}
