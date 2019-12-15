package duke.task;

/**
 * Storage deals with loading tasks from a file and saving tasks to the file.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Tasks are stored in the /data/duke.txt file.
     * This method checks if the file exists. If it does not, a new file
     * is created.
     * The method below is adapted from: https://github.com/WilliamRyank/duke/blob/master/src/main/java/Storage.java
     * after permission is obtained from original author.
     */
    public void checkFileExists() {
        try {
            String folder = "data";
            File directory = new File(folder);
            if (!directory.exists()) {
                directory.mkdir();
            }

            File taskFile = new File(filePath);
            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }

            assert taskFile.exists() : "File should exist now!";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from specified file.
     * @return ArrayList containing tasks saved in specified file
     */
    public ArrayList<Task> load() {
        try {
            checkFileExists();
            File taskFile = new File(filePath);

            Scanner sc = new Scanner(taskFile);
            ArrayList<Task> taskList = new ArrayList<>();

            //Loads task from hard disk to ArrayList
            while (sc.hasNextLine()) {
                Task taskFromFile = convertToTask(sc.nextLine());
                taskList.add(taskFromFile);
            }

            return taskList;
        } catch (IOException e) {

            System.out.println(filePath + " cannot be found!");
            e.printStackTrace();
            return new ArrayList<Task>();
        }
    }

    /**
     * Parses text file contents to a Task object.
     * @param task String representation of task
     * @return Task object representing task
     */
    private Task convertToTask(String task) {
        String[] taskInformation = task.split("\\|");
        String typeOfTask = taskInformation[0];
        String completionStatus = taskInformation[1];
        String taskDescription = taskInformation[2];
        Task convertedTask;
        if (typeOfTask.equals("[T]")) {
            convertedTask = new ToDo(taskDescription);
        } else if (typeOfTask.equals("[E]")) {
            convertedTask = new Event(taskDescription, taskInformation[3]);
        } else {
            assert typeOfTask.equals("[D]") : "typeOfTask cannot be " + typeOfTask;
            convertedTask = new Deadline(taskDescription, taskInformation[3]);
        }

        if (completionStatus.equals("1")) {
            convertedTask.markAsDone();
        }

        return convertedTask;
    }

    /**
     * Writes tasks to the file.
     * @param taskList TaskList containing tasks to write to file
     */
    public void writeTaskToFile(TaskList taskList) {
        try {
            FileWriter taskFile = new FileWriter(filePath);
            ArrayList<Task> taskArrayList = taskList.getTaskList();
            for (Task taskToWrite : taskArrayList) {
                taskFile.write(taskToWrite.writeToFile() + "\n");
            }

            taskFile.close();
        } catch (IOException ex) {
            System.out.println(filePath + " cannot be found!");
        }
    }
}
