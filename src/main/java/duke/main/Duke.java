package duke.main;

import duke.command.Command;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidParameterException;
import duke.exception.InvalidDateTimeException;
import java.io.IOException;
import duke.parser.CommandParser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Duke Program is a command line interface for a simple task manager. Users can add a todo item with just its
 * description, a deadline item with description and the date to complete it by, an event item with description and
 * the date the event is held at. The Duke Program has its own user interface, with its own storage and a list of
 * tasks list to provide easy tracking for the user.
 */
public class Duke {
    /**
     * This is the storage / database for Duke.
     */
    private final Storage storage;
    /**
     * This is a list of tasks. Operations can be performed in the TaskList class.
     */
    private TaskList tasks;
    /** UI
     * This is the user interface for Duke.
     */
    private final Ui ui;

    /**
     * Constructs a new Duke Object with the specified file path as its database / storage.
     * @param filePath This is the file path used to store the tasks. It also acts as the database / storage of the Duke
     *                 Program.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException ie) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * This runs the entire Duke Program. Once duke has started running, user can start typing in commands to duke. The
     * execution of individual {@link duke.command.Command} in Duke happens here.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = CommandParser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (InvalidCommandException ice) {
                ui.showError("Invalid command: " + ice.getInvalidCommand());
            } catch (InvalidParameterException ipe) {
                ui.showError("Invalid parameters: " + ipe.getInvalidParameter());
            } catch (InvalidDateTimeException idte) {
                ui.showError("Invalid date and time: " + idte.getInvalidDateTime());
            }
        }

    }

    /**
     * This is the main method of the Duke Program.
     * @param args the arguments to be passed into the Duke program before the program is run.
     */
    public static void main(String[] args) {
        new Duke("/Users/bj/java/Duke/data/duke.txt").run();
    }

}
