package duke.util;
/**
 * Class to handle storage of data
 */

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;


public class Storage {
    private String path;

    /**
     * Constructor for Storage object
     * @param path path to save/read history relative to current directory
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Retrieves history of tasklist
     * @return History of tasklist if present
     * @throws IOException if there are errors reading the file
     */
    public ArrayList<Task> retrieveHistory() throws IOException {
        Path filePath = Paths.get("./saved/taskList_history.txt");
        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(filePath));
        ArrayList<Task> taskList = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts[0].equals("T")) {
                taskList.add(new Todo(parts[2], parts[1].equals("1")));
            } else if (parts[0].equals("D")) {
                taskList.add(new Deadline(parts[2], parts[3], parts[1].equals("1")));
            } else if (parts[0].equals("E")) {
                taskList.add(new Event(parts[2], parts[3], parts[1].equals("1")));
            }
        }
        return taskList;
    }

    /**
     * Saves the history before closing the app
     * @param taskList tasklist to be saved as a .txt file
     * @throws IOException if there are errors writing to the file
     */
    public void saveHistory(ArrayList<Task> taskList) throws IOException {
        String result = "";
        for (Task t : taskList) {
            String type = "";
            String date = "";
            if (t instanceof Todo) {
                type = "T";
            } else if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                type = "D";
                date = d.getDate();
            } else if (t instanceof Event) {
                Event e = (Event) t;
                type = "E";
                date = e.getDate();
            }
            String completed = t.isCompleted() ? "1" : "0";
            String title = t.getName();
            if (t instanceof Todo) {
                String currentTask = type + "|" + completed + "|" + title + "\n";
                result += currentTask;
            } else {
                String currentTask = type + "|" + completed + "|" + title + "|" + date + "\n";
                result += currentTask;
            }
        }
        Path filePath = Paths.get("./saved/taskList_history.txt");
        Files.write(filePath, result.getBytes(), StandardOpenOption.CREATE);
    }

    /**
     * Checks if tasklist history exists
     * @return true if tasklist history exists, false otherwise
     */
    public boolean historyExists() {
        Path filePath = Paths.get("./saved/taskList_history.txt");
        return Files.exists(filePath);
    }

    /**
     * Creates a new .txt file to write history to
     * @throws IOException if there are errors creating the file
     */
    public void createFile() throws IOException {
        Path filePath = Paths.get("./saved/taskList_history.txt");
        Path folderPath = Paths.get("./saved/");
        Files.createDirectory(folderPath);
        Files.createFile(filePath);
    }
}
