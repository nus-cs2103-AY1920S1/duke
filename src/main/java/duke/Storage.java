package duke;

import duke.exception.DukeException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage instance.
     */
    Storage() {
        String separator = File.separator;
        this.filePath = String.format(
                "%s%s%s%s%s",
                System.getProperty("user.dir"), separator, "data", separator, "duke.txt"
        );
    }

    /**
     * Modifies the path of the storage instance.
     *
     * @param fileName Save file name.
     */
    public void setPath(String fileName) {
        String separator = File.separator;
        this.filePath = String.format(
                "%s%s%s%s%s",
                System.getProperty("user.dir"), separator, "data", separator, fileName
        );
    }

    /**
     * Loads tasks from save file.
     *
     * @return TaskList with all saved tasks.
     */
    TaskList load() {
        try {
            FileInputStream fin = new FileInputStream(this.filePath);
            ObjectInputStream ois = new ObjectInputStream(fin);

            return (TaskList) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new TaskList();
        }
    }

    /**
     * Writes tasks to save file.
     *
     * @param tasks TaskList with current tasks.
     * @throws DukeException If file I/O fails.
     */
    void persist(TaskList tasks) throws DukeException {
        try {
            // Create file if it does not exist
            File file = new File(this.filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();

            FileOutputStream fout = new FileOutputStream(file, false);
            ObjectOutputStream oos = new ObjectOutputStream(fout);

            oos.writeObject(tasks);
            fout.close();
        } catch (IOException e) {
            throw new DukeException("Failed to write to save file.");
        }
    }
}
