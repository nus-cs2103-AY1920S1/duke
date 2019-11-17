package duke.main;

import duke.command.Command;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidParameterException;
import duke.parser.CommandParser;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;
import duke.ui.UserInterface;

import java.io.IOException;

/**
 * The Duke Program is a java program for a simple task manager. Users can add a todo item with just its
 * description, a deadline item with description and the date to complete it by, an event item with description and
 * the date the event is held at. The Duke Program has a seperate user interface, storage and task manager to handle
 * different tasks.
 */
public class Duke {
    /**
     * This is the storage / database for Duke.
     */
    private final Storage storage;
    /**
     * This is the task manager to manage the different tasks. Operations can be performed in the TaskManager class.
     */
    private TaskManager tasks;
    /** UI
     * This is the user interface for Duke.
     */
    private final UserInterface userInterface;

    /**
     * Constructs a new duke instance by first loading the storage and creating a new task manager to manage different
     * types of task and its relevant operation.
     */
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

    /**
     * Run duke with user input.
     * @param userInput the user input to pass into the duke program
     * @return the output of the duke program
     */
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
