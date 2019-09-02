package duke;

import duke.command.Parser;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;

import java.io.FileNotFoundException;

/**
 * The main class for this program, calls the execution of the whole program.
 */
public class Duke {

    private static Ui ui;
    private static TaskList taskList;
    private static Storage storage;
    private static Parser parser;

    /**
     * Constructs a duke object with a given filepath to save the output.
     * @param filePath path to save the output text
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList  = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
        parser = new Parser(taskList, ui);
    }

    /**
     * Starts the program.
     */
    public void run() {
        ui.start(parser, storage, taskList);
    }

    /**
     * Main method for the whole program that creates a new Duke object and calls run().
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
