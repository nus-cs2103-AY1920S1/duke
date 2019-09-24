import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a storage in which the data is to be load and rewrite everytime the user use the duke application.
 */
public class Storage {

    private static File file;
    private static ArrayList<Task> listOfTasks;

    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Loads a file to populate the list of tasks.
     * If the file does not exist, a new file will be created
     *
     * @return the list of tasks
     */
    public static ArrayList<Task> load() {
        listOfTasks = new ArrayList<>();
        try {
            checkFileExist();
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] userInput = sc.nextLine().split("\\|");
                String taskType = userInput[0].trim();
                handleLoadTask(userInput, taskType);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return listOfTasks;
    }

    /**
     * Saves current list of tasks to hard drive.
     *
     * @param listOfTasks List that will be saved to the file.
     */
    public static void save(List<Task> listOfTasks) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : listOfTasks) {
                String taskToAdd;
                if (task instanceof Deadline) {
                    taskToAdd = String.format("D | %s | %s | %s\n", task.isTaskDone() ? "1" : "0",
                        task.getTaskDescription(), ((Deadline) task).getTaskBy());
                } else if (task instanceof Event) {
                    taskToAdd = String.format("E | %s | %s | %s\n", task.isTaskDone() ? "1" : "0",
                        task.getTaskDescription(), ((Event) task).getTaskAt());
                } else {
                    taskToAdd = String.format("T | %s | %s\n", task.isTaskDone() ? "1" : "0",
                        task.getTaskDescription());
                }
                fw.write(taskToAdd);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if the file exists, if not create a new file.
     *
     */
    private static void checkFileExist() {
        try {
            String folder = "data";
            File directory = new File(folder);
            if (!directory.exists()) {
                directory.mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            assert file.exists() : "File should exist now";
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles reading a task from the file into the list of tasks.
     *
     * @param userInput User input that has been parsed into a string array
     * @param taskType Type of the task that will be read.
     */
    private static void handleLoadTask(String[] userInput, String taskType) {
        try {
            boolean isTaskDone = userInput[1].trim().equals("1") ? true : false;
            String taskDescription = userInput[2].trim();
            if (taskType.equals("T")) {
                listOfTasks.add(new Todo(taskDescription, isTaskDone));
            } else if (taskType.equals("E")) {
                String taskAt = userInput[3].trim();
                listOfTasks.add(new Event(taskDescription, isTaskDone, Parser.dateTimeConverter(taskAt)));
            } else if (taskType.equals("D")) {
                String taskBy = userInput[3].trim();
                listOfTasks.add(new Deadline(taskDescription, isTaskDone, Parser.dateTimeConverter(taskBy)));
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

}