package duke.util;

import duke.exception.DukeException;
import duke.task.TaskList;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

public class Storage {
    // static attributes
    final static private String DEFAULT_SAVE_PATH = "./saved_lists/savestate.tmp";

    // object attributes
    private File file;

    public Storage() {
        this.file = new File(Storage.DEFAULT_SAVE_PATH);
    }

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public TaskList loadFromDisk() throws DukeException {
        TaskList list = null;
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

    public void saveToDisk(TaskList list) throws DukeException{
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
