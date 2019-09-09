package storage;

import duke.task.tasks.Task;
import error.storage.StorageException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileSystemStorage implements Storage {
    private String storageFilePath;

    private FileSystemStorage(String storageFilePath) {
        this.storageFilePath = storageFilePath;
    }

    public static FileSystemStorage getInstance(String storageFilePath) throws StorageException {
        FileSystemStorage storage = new FileSystemStorage(storageFilePath);
        storage.setup();

        return storage;
    }

    private void setup() throws StorageException {
        try {
            // Create new file if it doesn't exist
            if (!fileExists(storageFilePath)) {

                FileOutputStream outputStream = new FileOutputStream(storageFilePath);
                ObjectOutputStream taskWriter = new ObjectOutputStream(outputStream);

                System.out.println("Creating new storage file.");
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
