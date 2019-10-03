package duke.util;

import duke.exception.DukeException;
import duke.task.TaskList;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;


/**
 * Class that handles all methods related to the loading and saving of task lists
 * to the hard disk for persistent storage.
 */
public class Storage {
    // CLASS ATTRIBUTES
    // path to a config file which contains a path to the task list that duke should startup with
    private static final String CONFIG_PATH = "./config/config.txt";
    // the default path to be used if no config file is found
    private static final String DEFAULT_SAVE_PATH = "./saved_lists/default.tmp";


    // OBJECT ATTRIBUTES
    private File file;

    /**
     * Returns a Storage object. Uses the saved path in CONFIG_PATH for loading a TaskList that was last used,
     * or the default path in DEFAULT_SAVE_PATH if no such config file was previously
     * created.
     */
    public Storage() {
        try {
            // if a config file containing a last-used startup path is present, use it
            BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH));
            String listFilePath = reader.readLine();
            this.file = new File(listFilePath);
            reader.close();
        } catch (IOException e) {
            // else, use the default recommended path for loading and saving lists
            this.file = new File(Storage.DEFAULT_SAVE_PATH);
        }
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
            this.updateStartupConfig();
        } catch (IOException e) {
            throw new DukeException("I'm so sorry! I had trouble sync-ing the task list"
                    + " to the disk!\n"
                    + "Any changes might not be saved ):", e);
        } catch (ClassNotFoundException e) {
            throw new DukeException("I'm sorry, I couldn't decipher the saved list at:\n"
                    + this.file.getPath() + "\n"
                    + "It seems to be missing or corrupted...\n"
                    + "I will have to start a new list!", e);
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
            this.updateStartupConfig();
        } catch (IOException | NullPointerException e) {
            throw new DukeException("I'm so sorry! I had trouble saving the task list"
                    + "to the following path:\n"
                    + this.file.getPath() + "\n"
                    + "Any changes might not be saved ):", e);
        }
    }

    /**
     * Updates the duke config file with the last altered/used save path location. Upon the next
     * startup, duke will read from the config file to automatically load the task list
     * that was being used most recently.
     *
     * @throws DukeException if unable to create/write to config file (eg. due to permissions)
     * @see Storage#saveToDisk(TaskList)
     * @see Storage#loadFromDisk()
     */
    private void updateStartupConfig() throws DukeException {
        try {
            File config = new File(CONFIG_PATH);
            config.getParentFile().mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter(config));
            writer.write(this.file.getPath());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new DukeException("I couldn't change my config file in '/config/config.txt'!\n"
                    + "Did you restrict the write permissions to my own file?!", e);
        }
    }

    /**
     * Returns the save path location of the current TaskList object on the disk.
     *
     * @return String representation of the saved file path
     */
    public String getSavePath() {
        assert (this.file != null);
        return this.file.getPath();
    }

    /**
     * Changes the file that the current TaskList object is to be serialized to on disk.
     * Note that the actual saving of the TaskList object does not take place here, and will
     * be left to the implementation of the various data manipulation Command sub-classes.
     *
     * @param newPath the new save path to be saved to
     * @return the previous save path of the current TaskList object
     * @see duke.command.SaveCommand
     * @see duke.command.LoadCommand
     */
    public String changeSavePath(String newPath) {
        assert (this.file != null);
        String previousPath = this.file.getPath();
        this.file = new File(newPath);
        return previousPath;
    }
}
