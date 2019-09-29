package duke.core;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates a Storage object to deal with loading tasks from the file
 * and saving tasks in the file.
 */

public class Storage {

    /** 1 attribute.
     * file that stores the existing tasks.
     */
    private File file;

    /**
     * The constructor takes in filePath and creates a new storage object.
     * @param filePath the string representing the file path of the stored task list.
     */
    Storage(String filePath) {
        this.file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    /**
     * Loads the existing file from storage.
     * @return ArrayList of Task objects containing list from storage.
     * @throws DukeException if file does not exist.
     */
    ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file.getPath());
            Scanner s = new Scanner(fr);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] part = line.split("/");

                // Status is either done or not done.
                String status = part[1];
                boolean isDone;
                isDone = status.equals("✓");

                // Checks the type of task.
                switch (part[0]) {
                case "T":
                    tasks.add(new Todo(part[2], isDone));
                    break;
                case "D":
                    tasks.add(new Deadline(part[2], isDone, part[3]));
                    break;
                case "E":
                    tasks.add(new Event(part[2], isDone, part[3]));
                    break;
                default:
                    assert false : part[0];
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("☹ OOPS!!! Duke can't find the file.");
        }
        return tasks;
    }

    /**
     * Saves the tasks in the list whenever there is any change.
     * @param tasks TaskList object containing existing tasks.
     * @throws DukeException if the storage file does not exist.
     */
    public void updateFile(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file.getPath());
            for (Task task : tasks.getTaskList()) {
                fw.write(task.storageFormat() + "\n");
                fw.flush();
            }
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Duke can't find the file.");
        }
    }
}
