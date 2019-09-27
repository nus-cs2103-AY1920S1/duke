package duke.storage;

import duke.exception.FileLoadingException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Storage object that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    /**
     * The path to the file that contains the tasks.
     */
    private static String filePath = "../main/data/taskslist.txt";
    private static String directoryPath = "../main/data";
    private static DateTimeFormatter formatter;

    /**
     * An empty constructor that creates a Storage object.
     */
    public Storage() {
        formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    }

//    /**
//     * Creates a Storage object with the specified file path.
//     * @param filePath The path to the file that contains the tasks.
//     */
//    public Storage(String filePath) {
//        Storage.filePath = filePath;
//        formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
//    }

    /**
     * Loads the tasks from the file and stores it in an ArrayList.
     * @return The list of tasks that was read from the file.
     * @throws FileLoadingException if the file cannot be loaded.
     * @throws FileNotFoundException if the file cannot be found in the specified path.
     */
    public ArrayList<Task> load() throws FileLoadingException, FileNotFoundException {
        File taskFile = new File(filePath);
        File dataDirectory = new File(directoryPath);
        try {
            if (!taskFile.isFile()) {
                dataDirectory.mkdirs();
                taskFile.createNewFile();
            }
        } catch (IOException e) {
            throw new FileLoadingException("got error when loading file leh!!");
        }
//        try {
//            if (!taskFile.getAbsoluteFile().exists()) {
//                dataDirectory.mkdir();
//                taskFile.createNewFile();
//            }
//        } catch (IOException e) {
//            throw new FileLoadingException("got error when loading file leh!!");
//        }

        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(taskFile);

        while (sc.hasNext()) {
            String line = sc.nextLine(); // eg. T,false,read book
            String[] taskDetails = line.split(",");
            // to separate the line into its different components
            // namely, the taskType, isDone boolean value, description and the deadline/event time and day
            String taskType = taskDetails[0];
            boolean isDone = Boolean.parseBoolean(taskDetails[1]);

            switch (taskType) {
            case "T":
                String todoDesc = taskDetails[2];
                Todo todo = new Todo(todoDesc, isDone);
                list.add(todo);
                break;
            case "D":
                String deadlineDesc = taskDetails[2];
                String due = taskDetails[3];
                Deadline deadline = new Deadline(deadlineDesc, isDone, due);
                list.add(deadline);
                break;
            case "E":
                String eventDesc = taskDetails[2];
                String when = taskDetails[3];
                Event event = new Event(eventDesc, isDone, when);
                list.add(event);
                break;
            default:
                break;
            }
        }

        return list;
    }

    /**
     * Saves the tasks in the file by overwriting them.
     * @throws IOException if an I/O error occurs.
     */
    public void updateTasks() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        TaskList taskList = new TaskList();
        ArrayList<Task> tasks = taskList.getTaskList();
        StringBuilder fileContent = new StringBuilder();

        for (Task task : tasks) {
            fileContent.append(formatTask(task));
            fileContent.append(System.lineSeparator());
        }

        fw.write(fileContent.toString());
        fw.close();
    }

    /**
     * Converts specified task to its string representation.
     * @param task the specified task to be converted to its string representation.
     * @return the string representation of the specified task.
     */
    private String formatTask(Task task) {
        boolean isDone = task.isDone();
        String desc = task.getDesc();

        if (task instanceof Todo) {
            return String.format("%s,%b,%s", "T", isDone, desc);
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            LocalDateTime due = deadline.getDueBy();
            String dueAsString = convertDateTimeToString(due);
            return String.format("%s,%b,%s,%s", "D", isDone, desc, dueAsString);
        } else {
            Event event = (Event) task;
            LocalDateTime when = event.getWhen();
            String whenAsString = convertDateTimeToString(when);
            return String.format("%s,%b,%s,%s", "E", isDone, desc, whenAsString);
        }
    }

    public static String convertDateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }

    public static LocalDateTime convertStringToDateTime(String string) {
        return LocalDateTime.parse(string.trim(), formatter);
    }

}
