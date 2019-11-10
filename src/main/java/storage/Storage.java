package storage;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that handles the loading and writing of tasks from/to a text file in the hard drive.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for the storage object.
     *
     * @param filePath string representation of the file path from the project source directory.
     */
    public Storage(String filePath) {
        assert !filePath.equals("") : "filePath should not be empty";
        this.filePath = filePath;
    }

    /**
     * Updates the textfile with the latest tasklist by rewriting the entire tasklist.
     *
     * @param tasks tasklist that represents all the current tasks in the tasklist.
     */
    public void updateFile(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTaskList();
        try {
            File f = new File(filePath);
            FileWriter fw = new FileWriter(f, false);
            for (Task tk : taskList) {
                fw.write((tk instanceof Deadline ? "D"
                        : (tk instanceof Event ? "E"
                        : "T"))
                        + " | " + (tk.getDoneStatus() ? "    Done" : "Not Done")
                        + " | " + tk.getDescription() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Loads the tasklist from the textfile in the hard drive.
     *
     * @return an arraylist consisting of the tasks parsed.
     * @throws FileNotFoundException exception thrown when the text file cannot be found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<>();

        while (sc.hasNext()) {
            String[] details = sc.nextLine().split("(\\s\\|\\s)");

            Task curTask;
            String taskType = details[0];
            String description = details[2];


            if (taskType.equals("T")) {
                curTask = new ToDo(description);
            } else {
                String dateTime = details[3];
                if (taskType.equals("D")) {
                    curTask = new Deadline(description, dateTime);
                } else {
                    curTask = new Event(description, dateTime);
                }
            }

            if (details[1].equals("    Done")) {
                curTask.markDone();
            }
            taskList.add(curTask);
        }
        return taskList;
    }

}
