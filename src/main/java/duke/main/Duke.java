package duke.main;

import duke.command.Command;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidParameterException;
import duke.exception.InvalidDateTimeException;
import duke.parser.CommandParser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.ui.UserInterface;
import javafx.application.Platform;

import java.io.IOException;

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
    private final UserInterface userInterface;

    public Duke() {
        userInterface = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException ie) {
            userInterface.showLoadingError();
            tasks = new TaskList();
        }

    }

    public String runWithUserInput(String userInput) {
        boolean isExit = false;
        try {
            String fullCommand = userInterface.readCommand(userInput);
            Command c = CommandParser.parse(fullCommand);
            return c.execute(tasks, userInterface, storage);
        } catch (InvalidCommandException ice) {
            return userInterface.showError("Invalid command: " + ice.getInvalidCommand());
        } catch (InvalidParameterException ipe) {
            return userInterface.showError("Invalid parameters: " + ipe.getInvalidParameter());
        } catch (InvalidDateTimeException idte) {
            return userInterface.showError("Invalid date and time: " + idte.getInvalidDateTime());
        }
    }

    /**
     * This runs the entire Duke Program. Once duke has started running, user can start typing in commands to duke. The
     * execution of individual {@link duke.command.Command} in Duke happens here.
     */
    public void run() {
        userInterface.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = userInterface.readCommand();
                Command c = CommandParser.parse(fullCommand);
                c.execute(tasks, userInterface, storage);
                isExit = c.isExit();
            } catch (InvalidCommandException ice) {
                userInterface.showError("Invalid command: " + ice.getInvalidCommand());
            } catch (InvalidParameterException ipe) {
                userInterface.showError("Invalid parameters: " + ipe.getInvalidParameter());
            } catch (InvalidDateTimeException idte) {
                userInterface.showError("Invalid date and time: " + idte.getInvalidDateTime());
            }
        }

    }

    /**
     * This is the main method of the Duke Program.
     * @param args the arguments to be passed into the Duke program before the program is run.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
