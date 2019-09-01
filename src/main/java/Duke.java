import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import Tasks.TaskList;

import Exception.DukeException;
import Exception.IncorrectTaskNameException;
import Exception.EmptyDeadlineDescriptionException;
import Exception.EmptyEventDescriptionException;
import Exception.EmptyTodoDescriptionException;

import Util.Parser;
import Util.Storage;
import Util.Ui;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the main class for the project where the main method will run
 */
public class Duke {
    public static ArrayList<Task> storedTasks = new ArrayList<>();
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static void main(String[] args) throws Exception {
        new Duke("data/duke.txt").run();
    }

    /**
     * Constructs a Duke object which will initialise 1)storage, 2)tasks 3)Ui class..
     *
     * @param filePath is the file directory for where duke.txt is saved
     */
    public Duke(String filePath) throws Exception, DukeException {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
            ui = new Ui(tasks, storage);
        } catch (Exception e) {
            throw new DukeException("File is empty");
        }
    }

    /**
     * runs the Ui read input to take in data from user.
     *
     */

    public void run() throws Exception{
        ui.readInput();
        storage.closeFile();
    }
}
