package seedu.duke;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.Command;
import seedu.duke.controllers.DialogBox;
import seedu.duke.exceptions.DukeException;
import seedu.duke.helpers.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.ui.Ui;

/**
 * The Duke Assistant keeps track of 'trackables' such as ToDos, Events and Deadlines.
 * The CLI Interface makes it ideal for a no distraction environment.
 *
 * @since 2019-08-13
 */

public class Duke {

    private TaskList tasks;

    /**
     * Constructs a new Duke Object.
     * TaskList is instantiated by loading from Storage if data exists.
     * If existing data does not exist or ends in an exception, an empty TaskList is returned.
     */
    public Duke() {
        // Load from disk
        try {
            tasks = Storage.getInstance().loadFromDisk();
        } catch (Storage.StorageOperationException e) {
            Ui.printError(e);
            Ui.printLoadingError();
            tasks = new TaskList();
        }
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
    public void run() {
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

    public String handleCommand(String input) {
        try {
            Command commandToExecute = Parser.parseCommand(input);
            String response = commandToExecute.execute(tasks);

            if (commandToExecute.getClass() == ByeCommand.class) {
                Storage.getInstance().saveToDisk(tasks);
                System.exit(0);
            }

        } catch (DukeException e) {
            return e.getMessage();
        }

        return "";
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
