import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

import java.util.List;

public class Storage {
    // static attributes
    final static private String DEFAULT_SAVE_PATH = "../saved/savestate.tmp";

    // object attributes
    private File file;

    public Storage() {
        this.file = new File(Storage.DEFAULT_SAVE_PATH);
    }

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /** Attempts to load a saved task list from the disk.
     *
     * @return List<Task> object successful, null otherwise
     */
    public List<Task> loadFromDisk() throws DukeException {
        List<Task> list = null;
        boolean success = false;
        try {
            FileInputStream fis = new FileInputStream(this.file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (List<Task>) ois.readObject();
            ois.close();
            success = true;
        } catch (IOException e) {
            ; // no action needs to be done here, return value will indicate failure
        } catch (ClassNotFoundException e) {
            throw new DukeException("I'm sorry, I couldn't decipher the saved list.\n"
                    + "It seems to be corrupted...\n"
                    + "I will have to start a new list!");
        }
        return list;
    }

    /**
     * Saves the current task list stored in Duke to the disk.
     *
     */
    public void saveToDisk(List<Task> list) throws DukeException{
        boolean success = false;
        try {
            this.file.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(this.file, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            success = true;
        } catch (IOException e) {
            throw new DukeException("I'm so sorry! I had trouble sync-ing the task list"
                    + "to the disk!\n"
                    + "Any changes might not be saved ):");
        }
    }
}
