package duke.util;

import duke.exception.DukeException;
import duke.task.TaskList;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;


/**
 * Class that handles all methods related to the loading and saving of task lists
 * to the hard disk for persistent storage.
 */
public class Storage {
    // static attributes
    private static final String DEFAULT_SAVE_PATH = "./saved_lists/savestate.tmp";

    // object attributes
    private File file;

    /**
     * Returns a Storage object that uses the default duke save path.
     */
    public Storage() {
        this.file = new File(Storage.DEFAULT_SAVE_PATH);
    }

    /**
     * Returns a Storage object that uses the filePath provided by the user
     * for storing and loading the task list.
     * @param filePath String containing os filepath
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Returns a TaskList saved in the stored directory. The filePath
     * is taken to be the default filePath specified by Storage.DEFAULT_SAVE_PATH,
     * or a user specified one upon initialization of the Storage object.
     * If unsuccessful, throws an exception.
     *
     * @return TaskList object loaded from disk, if successful.
     * @throws DukeException if loading fails.
     */
    public TaskList loadFromDisk() throws DukeException {
        TaskList list;
        try {
            FileInputStream fis = new FileInputStream(this.file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (TaskList) ois.readObject();
            ois.close();
        } catch (IOException e) {
            throw new DukeException("I'm so sorry! I had trouble sync-ing the task list"
                    + "to the disk!\n"
                    + "Any changes might not be saved ):");
        } catch (ClassNotFoundException e) {
            throw new DukeException("I'm sorry, I couldn't decipher the saved list.\n"
                    + "It seems to be corrupted...\n"
                    + "I will have to start a new list!");
        }
        return list;
    }

    /**
     * Serializes and saves the current TaskList object to the hard disk.
     * Filepath used is the one provided during initialization, or the default
     * filepath found in Storage.DEFAULT_SAVE_PATH if not supplied. Throws an exception
     * upon failure
     *
     * @param list TaskList object to be saved.
     * @throws DukeException if saving was unsuccessful.
     */
    public void saveToDisk(TaskList list) throws DukeException {
        try {
            this.file.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(this.file, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
        } catch (IOException e) {
            throw new DukeException("I'm so sorry! I had trouble sync-ing the task list"
                    + "to the disk!\n"
                    + "Any changes might not be saved ):");
        }
    }
}
