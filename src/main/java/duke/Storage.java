package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * This class is responsible for all storage methods for duke, storing
 * and saving the task list to the local drive for data persistence.
 */
public class Storage {
    private File directoryPath;
    private File saveFile;

    /**
     * Returns an instance of Storage for duke.
     * @param filepath represents the file path for the data.
     */
    public Storage(String filepath) {
        this.directoryPath = new File(filepath);
        directoryPath.mkdirs();
        this.saveFile = new File(filepath + File.separator + "list.bin");
    }

    /**
     * Loads a TaskList from the storage binary at the designated file path.
     * @return a TaskList object loaded from the binary.
     * @throws DukeException when an existing save was not found.
     */
    public TaskList load() throws DukeException {
        try {
            FileInputStream load = new FileInputStream(this.saveFile);
            ObjectInputStream loadList = new ObjectInputStream(load);
            TaskList taskList = (TaskList) loadList.readObject();
            loadList.close();
            return taskList;
        } catch (Exception e) {
            throw new DukeException("Existing save not found! "
                    + "Creating new task list.");
        }
    }

    /**
     * Saves a TaskList to the storage binary at the designated file path.
     * @param taskList a TaskList object to be saved into the binary.
     * @throws DukeException if save fails (either due to permissions
     *     or storage space)
     */
    public void save(TaskList taskList) throws DukeException {
        try {
            FileOutputStream save = new FileOutputStream(this.saveFile);
            ObjectOutputStream saveList = new ObjectOutputStream(save);
            saveList.writeObject(taskList);
            saveList.close();
        } catch (Exception e) {
            throw new DukeException("Failed to save!");
        }
    }
}
