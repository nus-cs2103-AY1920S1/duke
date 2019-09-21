package seedu.duke;

import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.DukeException;
import seedu.duke.helpers.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.ui.Ui;

/**
 * The Duke Assistant keeps track of 'trackables' such as ToDos, Events and Deadlines.
 * The CLI makes it ideal for a focused environment.
 */

public class Duke {

    private TaskList tasks;
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke Assistant.\nHow can I help?";

    /**
     * Constructs a new Duke Object.
     * TaskList is instantiated by loading from Storage if data exists.
     * If existing data does not exist or ends in an exception, an empty TaskList is returned.
     */
    public Duke() {

    }

    /**
     * Enables the deferred initialization of taskList.
     * @return The Welcome Message and an error message if the tasklist could not be restored from file.
     */
    public String initialize() {
        String message = WELCOME_MESSAGE + "\n";
        // Load from disk
        try {
            tasks = Storage.getInstance().loadFromDisk();
        } catch (Storage.StorageOperationException e) {
            tasks = new TaskList();
            return message +  e.getMessage();
        }
        assert (tasks != null);     // Duke's taskList should never be null after the constructor is complete.

        return message;
    }

    /**
     * seedu.duke.Main Method. Duke is instantiated and run here.
     *
     * @param args string arguments for console.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Executes the Duke Assistant.
     */
    private void run() {
        Ui.greet();

        //noinspection InfiniteLoopStatement
        while (true) {
            String input = Ui.readNextLine();
            try {
                Parser.parseCommand(input).execute(tasks);
            } catch (DukeException e) {
                Ui.printError(e);
            }
        }
    }

    /**
     * Handles the user input and returns the response from Duke.
     * @param input The user input
     * @return Returns the response from Duke.
     */
    public String handleCommand(String input) {
        try {
            Command commandToExecute = Parser.parseCommand(input);
            String response = commandToExecute.execute(tasks);

            if (commandToExecute.getClass() == ByeCommand.class) {
                Storage.getInstance().saveToDisk(tasks);
                System.exit(0);
            }

            return response;

        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
