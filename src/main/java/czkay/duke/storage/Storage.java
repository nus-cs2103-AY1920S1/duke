package czkay.duke.storage;

import czkay.duke.model.TaskList;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

/**
 * Manages the writing and reading of the task list to and from the hard drive.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the task list from the hard drive.
     *
     * @return The task list read from the hard drive.
     * @throws IOException If the I/O operation fails.
     * @throws ClassNotFoundException If the class is not found during runtime.
     */
    public TaskList load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        TaskList tasks = (TaskList) ois.readObject();
        ois.close();
        return tasks;
    }

    /**
     * Writes the task list to the hard drive.
     *
     * @param tasks The task list to be written to the hard drive.
     * @throws IOException If the I/O operation fails.
     */
    public void update(TaskList tasks) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(tasks);
        oos.close();
    }

}
