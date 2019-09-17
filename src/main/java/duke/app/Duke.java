package duke.app;

import duke.command.Command;
import duke.exception.DukeException;
import duke.history.History;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

import java.util.NoSuchElementException;

/**
 * Represents the Duke application object. Contains a user interface, a task list, and a
 * storage object to save and retrieve data from the hard drive.
 */
public class Duke {
    // Instance variables.
    private Ui ui;
    private Tasklist tasks;
    private History history;
    private Storage storage;

    /**
     * Constructs a Duke object with the save file initialised. Creates a new, empty
     * task list if the save file could not be initialised.
     */
    public Duke() {
        // Hard coded filepath for now.
        String filePath = "./data/tasks.txt";
        ui = new Ui();
        history =  new History();
        storage = new Storage(filePath);

        try {
            tasks = new Tasklist(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            ui.showLoadingError();
            tasks = new Tasklist();
        }
    }

    /**
     * Gets response from Duke. Runs the main logic flow of Duke for one command only.
     */
    public String getResponse(String input) {
        try {
            String[] fullCommand = input.split(" ", 2); // Split into command & details
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage, history);
            return c.commandOutput(); // commandOutput is the text passed to UI.

        } catch (NoSuchElementException | DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Welcomes user.
     */
    public String welcomeUser() {
        return ui.showWelcome();
    }
}
