package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Represents interaction between temporary duke data and hard disk.
 */
public class Storage {

    private String filePath;

    /**
     * Initiates a Storage object.
     * @param filePath path of the file where data is stored
     */
    Storage(String filePath) {
        this.filePath = filePath;

        // create data file if it doesn't exist
        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.out.print("");
        }
    }

    /**
     * Writes tasks to hard disk.
     * @param list a Tasklist object containing tasks
     * @throws DukeException if IOException occurs
     */
    public void write(TaskList list) throws DukeException {
        ArrayList<Task> tasks = list.tasks;
        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            StringBuilder content = new StringBuilder();
            for (Task t : tasks) {
                String type;
                if (t instanceof Deadline) {
                    type = "D";
                } else if (t instanceof Event) {
                    type = "E";
                } else {
                    assert t instanceof Todo : "unknown type of task";
                    type = "T";
                }
                String isDone = t.isDone() ? "1" : "0";
                String taskData = type + "@" + isDone + "@" + t.getDescription();
                content.append(taskData).append("\n");
            }
            fileWriter.write(content.toString());
        } catch (IOException e) {
            throw new DukeException("Cannot write to file");
        }
    }

    /**
     * Extracts information of tasks from hard disk.
     * @return an ArrayList of tasks that is stored in the file
     * @throws DukeException if IOException occurs
     */
    ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<Task>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("@");
                Task newTask;
                boolean isDone = parts[1].equals("1");
                switch (parts[0]) {
                case "T":
                    newTask = new Todo(parts[2], isDone);
                    break;
                case "E":
                    newTask = new Event(parts[2], isDone);
                    break;
                case "D":
                    newTask = new Deadline(parts[2], isDone);
                    break;
                default:
                    newTask = new Task("");
                    assert false : "unknown type of tasks";
                }
                list.add(newTask);
            }
        } catch (IOException e) {
            throw new DukeException("Cannot load from file");
        }
        return list;
    }
}