package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.exception.DukeException;

public class Storage {
    private String filePath;

    /**
     * Constructs a <code>Storage</code> object using filePath.
     *
     * @param filePath The filePath where the data is stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the existing tasks from the data file.
     *
     * @return An ArrayList of tasks which are stored in the data file
     * @throws DukeException when the file does not exit
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(" \\| ");
                Task task;

                switch (data[0]) {
                case "T":
                    task = new Todo(data[2]);
                    break;
                case "D":
                    task = new Deadline(data[2], data[3]);
                    break;
                case "E":
                    task = new Event(data[2], data[3]);
                    break;
                default:
                    throw new DukeException("Failed to load tasks.");
                }
                if (data[1].equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
            scanner.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found.");
        }
    }

    /**
     * Save the current tasks in the list to the data file.
     *
     * @param tasks The TaskList to be saved to the destined filePath
     * @throws DukeException when writing to file fails
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task task : tasks.getTasks()) {
                fw.write(task.serialize());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to save tasks.");
        }
    }
}