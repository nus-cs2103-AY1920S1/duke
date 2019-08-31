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

public class Duke {
    //variables
    public static ArrayList<Task> storedTasks = new ArrayList<>();
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    //main
    public static void main(String[] args) throws Exception {
        new Duke("data/duke.txt").run();
    }

    //implementation methods
    public Duke(String filePath) throws Exception, DukeException {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
            ui = new Ui(tasks, storage);
        } catch (Exception e) {
            throw new DukeException("File is empty");
        }
    }

    public void run() throws Exception{
        ui.readInput();
        storage.closeFile();
    }
}
