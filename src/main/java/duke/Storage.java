package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    /**
     * Deals with loading tasks from the given file and saving tasks in the same file.
     *
     * @param filePath The file path where the file is stored. It can be
     *                 either relative or absolute.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the existing tasks from the data file.
     *
     * @return An ArrayList of tasks which are stored as serialized string in the data file
     * @throws DukeException if the file does not exit or the data in the file
     *                       does not follow the correct format (i.e the serialized form of task objects)
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
     * Converts the current tasks in the list to serialized form and
     * save the each task to the file.
     *
     * @param tasks The list of tasks to be saved to the destined file
     * @throws DukeException if file writing fails
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