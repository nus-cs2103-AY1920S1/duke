package com.leeyiyuan.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter; 
import java.io.IOException;
import java.io.Writer;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.leeyiyuan.storage.format.DeadlineTaskFormatter;
import com.leeyiyuan.storage.format.EventTaskFormatter;
import com.leeyiyuan.storage.format.TaskFormatException;
import com.leeyiyuan.storage.format.TaskFormatter;
import com.leeyiyuan.storage.format.TaskParseException;
import com.leeyiyuan.storage.format.TodoTaskFormatter;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.Task;

public class Storage {

    protected String filePath;

    protected ArrayList<TaskFormatter> taskFormatters;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskFormatters = new ArrayList<TaskFormatter>();
        this.taskFormatters.add(new TodoTaskFormatter());
        this.taskFormatters.add(new DeadlineTaskFormatter());
        this.taskFormatters.add(new EventTaskFormatter());
    }

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
                        
                    }
                }
                if (task == null) {
                    throw new StorageException("Unreadable data in tasks file.");
                }
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {

        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return tasks;
    }

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

                    }
                }
                if (line == null) {
                    throw new StorageException("Unsupported task in tasks list.");
                }
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
