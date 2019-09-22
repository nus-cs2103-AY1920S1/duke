package duke.storage;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage to store, load and save data of user.
 */
public class Storage {
    /** Path of the file. */
    protected String filePath;

    /** File object of the filePath. */
    protected File dataFile;

    private static final String MESSAGE_NO_PREV_DATA = "No previously saved data.";

    /**
     * Initializes a Storage object with the specified file path.
     * @param filePath file path of the file to be used to save data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        dataFile = new File(filePath);
    }

    /**
     * Loads any previous data of the user.
     * Data is converted into an ArrayList of tasks.
     * @return ArrayList of tasks which were saved previously by the user.
     * @throws DukeException if there is error loading the data or there is
     *     no previous data.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            boolean shouldCreate = dataFile.createNewFile();

            if (shouldCreate) {
                throw new DukeException(MESSAGE_NO_PREV_DATA);
            }

            ArrayList<Task> tasks = new ArrayList<Task>();
            Scanner sc = new Scanner(dataFile);

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                Parser.parseLoad(data, tasks);
            }

            return tasks;
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Saves the list of tasks created by the user into the file.
     * @param tasks ArrayList of tasks to be saved.
     * @throws DukeException if there is error saving the tasks.
     */
    public void saveAll(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(dataFile);

            for (int i = 0; i < tasks.getSize(); i++) {
                Task currTask = tasks.getTask(i);
                fw.write(currTask.getSaveData());
                fw.write(System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
