package storage;

import duke.task.Task;
import error.storage.StorageException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage class that handles the reading and writing of tasks to a storage file found in the local machine's file
 * system. This class will create a new storage file in the file system of the local machine if it does not already
 * exist. If not, it will read and write to the file corresponding to specified path.
 */
public class FileSystemStorage implements Storage {
    private String storageFilePath;

    private FileSystemStorage(String storageFilePath) {
        this.storageFilePath = storageFilePath;
    }

    /**
     * Returns an instance of a FileSystemStorage that reads and writes to a file path. A new file is created
     * at the file path if it does not already exist.
     * @param storageFilePath file path to read and write tasks
     * @return FileSystemStorage instance
     * @throws StorageException if unable to read file path
     */
    public static FileSystemStorage getInstance(String storageFilePath) throws StorageException {
        FileSystemStorage storage = new FileSystemStorage(storageFilePath);
        storage.setup();

        return storage;
    }

    private void setup() throws StorageException {
        try {
            // Create new file if it doesn't exist
            if (!fileExists(storageFilePath)) {
                File file = new File(storageFilePath);

                file.getParentFile().mkdirs();

                FileOutputStream outputStream = new FileOutputStream(storageFilePath);
                ObjectOutputStream taskWriter = new ObjectOutputStream(outputStream);

                System.out.println("Creating new storage file...");
                taskWriter.writeObject(new ArrayList<Task>());
                taskWriter.close();
            }

        } catch (IOException e) {
            throw new StorageException("Unable to set up storage");
        }
    }

    private boolean fileExists(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * Gets stored tasks.
     * @return list of tasks
     * @throws StorageException if unable to read from file path
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Task> getTasks() throws StorageException {
        try {

            FileInputStream inputStream = new FileInputStream(storageFilePath);
            ObjectInputStream taskReader = new ObjectInputStream(inputStream);
            List<Task> tasks = (List<Task>) taskReader.readObject();

            taskReader.close();
            return tasks;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new StorageException("Unable to read tasks");
        }
    }

    /**
     * Writes tasks to storage file.
     * @param tasks list of task to be written
     * @throws StorageException if unable to write to file path
     */
    @Override
    public void writeTasks(List<Task> tasks) throws StorageException {
        try {

            FileOutputStream outputStream = new FileOutputStream(storageFilePath);
            ObjectOutputStream taskWriter = new ObjectOutputStream(outputStream);
            taskWriter.writeObject(tasks);

            taskWriter.close();

        } catch (IOException e) {
            throw new StorageException("Unable to save tasks");
        }
    }
}
