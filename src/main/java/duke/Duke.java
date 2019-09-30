package duke;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.dukeexception.DukeException;
import duke.command.Command;
import duke.parser.Parser;

/**
 * Main driver class. Handles user input and communicates with Parser, Ui, TaskList and Storage. Uses Command object
 * to implement underlying logic.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String ROOT_DIRECTORY = "C:/Users/gbrls/OneDrive/Desktop/duke-master/src/main/java/duke/";

    /**
     * Represents a new instance of Duke application, with associated Ui, Storage and TaskList instance variables.
     * @param filePath File path of text file containing save data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(ROOT_DIRECTORY + "data/tasks.text").run();
    }
}