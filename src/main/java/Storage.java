import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        File f;
        Scanner sc;
        try {
            f = new File(filePath);
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new DukeException("File loading error: " + e.getMessage());
        }

        ArrayList<Task> taskList = readFromFile(sc, f);
        return taskList;
    }

    private ArrayList<Task> readFromFile(Scanner sc, File f) throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        while (sc.hasNextLine()) {
            Task task = addToTaskList(sc.nextLine().trim());
            taskList.add(task);
        }
        return taskList;
    }

    private Task addToTaskList(String nextLine) throws DukeException {

        String[] arr = nextLine.split("\\|");

        if (arr.length < 3 || arr.length > 4) {
            throw new DukeException("Existing tasklist is corrupt.");
        }

        String taskType = arr[0].trim(); // Check task type
        if (!taskType.equals("T") && !taskType.equals("E") && !taskType.equals("D")) {
            throw new DukeException("Invalid task type found.");
        }

        int status;         // Check task status.
        try{
            status = Integer.parseInt(arr[1].trim());
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task status found.");
        }

        boolean isComplete = status==0 ? false : true;

        Task newTask;
        DateTime dateTime;
        try {
            switch (taskType) {
            case "T":
                newTask = new ToDo(arr[2], isComplete);
                break;
            case "E":
                dateTime = DateTime.parseTaskListFormat(arr[3].trim());
                newTask = new Event(arr[2], isComplete, dateTime);
                break;
            case "D":
                dateTime = DateTime.parseTaskListFormat(arr[3].trim());
                newTask = new Deadline(arr[2], isComplete, dateTime);
                break;
            default:
                newTask = null;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task status found.");
        }

        return newTask;
    }

    void save(TaskList tasks) throws DukeException {
        try {
            new FileWriter(filePath); //create new file
            for (Task task : tasks.getTasks()) {
                appendToFile(filePath, task.publishTask() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new DukeException("Error in saving to file: " + e.getMessage());
        }
    }

    private void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    void clearAll() throws DukeException {
        try {
            new FileWriter(filePath); //create new file
        } catch (IOException e) {
            throw new DukeException("File does not exist, there is nothing to clear!");
        }
    }
}