package duke.main;

import duke.command.Command;
import duke.exception.FailedToLoadIOException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidParameterException;
import duke.parser.CommandParser;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;
import duke.ui.UserInterface;

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
     * This is a list of tasks. Operations can be performed in the TaskManager class.
     */
    private TaskManager tasks;
    /** UI
     * This is the user interface for Duke.
     */
    private final UserInterface userInterface;

    public Duke() {
        userInterface = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskManager(storage.load());
        } catch (IOException ioe) {
            userInterface.showLoadingError();
            tasks = new TaskManager();
        }

    }

    public String runWithUserInput(String userInput) {
        try {
            String fullCommand = userInterface.readCommand(userInput);
            Command c = CommandParser.parse(fullCommand);
            return c.execute(tasks, userInterface, storage);
        } catch (InvalidCommandException invalidCommand) {
            return userInterface.showInvalidCommandError(invalidCommand);
        } catch (InvalidParameterException invalidParameters) {
            return userInterface.showInvalidParametersError(invalidParameters);
        }
    }

}
