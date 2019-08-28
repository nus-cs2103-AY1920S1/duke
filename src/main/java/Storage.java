import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    private static String filePath;

    /**
     * An empty constructor that creates a Storage object.
     */
    public Storage() {}

    /**
     * Creates a Storage object with the specified file path.
     * @param filePath The path to the file that contains the tasks.
     */
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Loads the tasks from the file and stores it in an ArrayList.
     * @return The list of tasks that was read from the file.
     * @throws FileNotFoundException if the file cannot be found in the specified path.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String line = sc.nextLine(); // eg. T,0,read book
            String[] taskDetails = line.split(",");
            String taskType = taskDetails[0];
            int booleanInt = Integer.parseInt(taskDetails[1]); // 0 or 1
            switch (taskType) {
            case "T":
                Todo todo = new Todo(taskDetails[2], booleanInt == 0);
                list.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(taskDetails[2], booleanInt == 0, taskDetails[3]);
                list.add(deadline);
                break;
            case "E":
                Event event = new Event(taskDetails[2], booleanInt == 0, taskDetails[3]);
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
    public void overwriteTasks() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        TaskList taskList = new TaskList();
        ArrayList<Task> tasks = taskList.getTaskList();
        String fileContent = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        for (Task task : tasks) {
            if (task instanceof Todo) {
                fileContent += "T," + (task.isDone() ? 0 : 1) + "," + task.getDesc();
                // format the task in the way it was read from the file
            } else if (task instanceof Deadline) {
                fileContent += "D," + (task.isDone() ? 0 : 1) + "," + task.getDesc() + ","
                        // format the date and time back to how it was read from the file
                        + ((Deadline)task).getDeadline().format(formatter);
            } else {
                fileContent += "E," + (task.isDone() ? 0 : 1) + "," + task.getDesc() + ","
                        + ((Event)task).getWhen().format(formatter);
            }
            fileContent += System.lineSeparator();
        }
        fw.write(fileContent);
        fw.close();
    }

}
