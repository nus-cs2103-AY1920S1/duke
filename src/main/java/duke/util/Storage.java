package duke.util;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parser class, to handle disk storage in Duke application.
 */
public class Storage {
    private File file;

    /**
     * Constructs a Storage object.
     *
     * @param filePath File path for disk storage.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Initializes the file for disk storage.
     */
    private void setup() throws DukeException {
        try {
            if (!this.file.exists()) {
                File directory = new File(this.file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                this.file.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Errors occurred while setting up database!");
        }
    }

    /**
     * Loads saved tasks from disk storage.
     *
     * @return an ArrayList of tasks corresponding to saved file in disk storage.
     * @throws DukeException if no file found in disk storage.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            Scanner sc = new Scanner(this.file);
            ArrayList<Task> taskList = new ArrayList<Task>();
            while (sc.hasNextLine()) {
                String[] description = sc.nextLine().split(" \\| ");
                Task task;
                switch (description[0]) {
                case "T":
                    assert description.length == 3 || description.length == 4 : "Wrong todo task format stored.";
                    task = new ToDo(description[2]);
                    if (description.length == 4) {
                        task.addTag(description[3]);
                    }
                    break;
                case "D":
                    assert description.length == 4 || description.length == 5 : "Wrong deadline task format stored.";
                    task = new Deadline(description[2], description[3]);
                    if (description.length == 5) {
                        task.addTag(description[4]);
                    }
                    break;
                case "E":
                    assert description.length == 4 || description.length == 5 : "Wrong event task format stored.";
                    task = new Event(description[2], description[3]);
                    if (description.length == 5) {
                        task.addTag(description[4]);
                    }
                    break;
                default:
                    throw new DukeException("Task description invalid!");
                }
                assert description[1].equals("0") || description[1].equals("1") : "Wrong task format stored.";
                if (description[1].equals("1")) {
                    task.markAsDone();
                }
                taskList.add(task);
            }
            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found!");
        }
    }

    /**
     * Saves tasks in disk storage.
     *
     * @param taskList ArrayList of tasks to be saved.
     * @throws DukeException if errors occurred due to IO exceptions.
     */
    public void store(ArrayList<Task> taskList) throws DukeException {
        try {
            this.setup();
            FileWriter fw = new FileWriter(this.file);
            for (Task task : taskList) {
                fw.write(task.toDataString() + '\n');
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Errors occurred while updating database!");
        }
    }
}
