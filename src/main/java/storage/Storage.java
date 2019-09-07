package com.leeyiyuan.storage;


import com.leeyiyuan.storage.format.DeadlineTaskFormatter;
import com.leeyiyuan.storage.format.EventTaskFormatter;
import com.leeyiyuan.storage.format.TaskFormatException;
import com.leeyiyuan.storage.format.TaskFormatter;
import com.leeyiyuan.storage.format.TaskParseException;
import com.leeyiyuan.storage.format.TodoTaskFormatter;
import com.leeyiyuan.task.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage for tasks persistence.
 */
public class Storage {

    /** Path of file used to back Storage. */
    protected String filePath;

    /** Fixed list of TaskFormatters. */
    protected ArrayList<TaskFormatter> taskFormatters;

    /**
     * Constructs a Storage using a file path.
     *
     * @param filePath Path of file used to back Storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskFormatters = new ArrayList<TaskFormatter>();
        this.taskFormatters.add(new TodoTaskFormatter());
        this.taskFormatters.add(new DeadlineTaskFormatter());
        this.taskFormatters.add(new EventTaskFormatter());
    }

    /**
     * Returns a list of task persisted by the Storage.
     *
     * @return List of task persisted by the Storage.
     * @throws StorageException If there was an error loading the tasks.
     */
    public ArrayList<Task> load() throws StorageException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File(this.filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = null;
                for (TaskFormatter taskFormatter : this.taskFormatters) {
                    try {
                        task = taskFormatter.parse(line);
                        break;
                    } catch (TaskParseException e) {
                        // Cannot parse with current TaskFormatter. Suppress error and move to next.
                    }
                }
                assert task != null;
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new StorageException("Tasks file not found.");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return tasks;
    }

    /**
     * Persists a list of tasks into the Storage.
     *
     * @param tasks List of tasks to persist.
     * @throws StorageException If tasks cannot be persisted into Storage.
     */
    public void save(ArrayList<Task> tasks) throws StorageException {
        FileWriter fileWriter = null;

        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
            fileWriter = new FileWriter(filePath, false);
            for (Task task : tasks) {
                String line = null;
                for (TaskFormatter taskFormatter : this.taskFormatters) {
                    try {
                        line = taskFormatter.format(task);
                        break;
                    } catch (TaskFormatException e) {
                        // Cannot format with current TaskFormatter. Suppress error and move to next.
                    }
                }
                assert line != null;
                fileWriter.write(line + "\n");
            }
        } catch (IOException e) {
            throw new StorageException("Error writing to file.");
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new StorageException("Error closing file.");
                }
            }
        }
    }
}
