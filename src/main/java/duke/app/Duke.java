package duke.app;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

import java.util.NoSuchElementException;

/**
 * Represents the Duke application object.
 */
public class Duke {
    // Instance variables.
    private Ui ui;
    private Tasklist tasks;
    private Storage storage;

    /**
     * Constructs a Duke object with the storage file initialised.
     */
    public Duke() {
        // Hard coded filepath for now.
        String filePath = "./data/tasks.txt";
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new Tasklist(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            ui.showLoadingError();
            tasks = new Tasklist();
        }
    }

    public static void main(String[] args) {
        new Duke().run();

    } // End of main.

    // Handler Methods.
    /**
     * Gets response from Duke. Runs the main logic flow of Duke for one command.
     */
    public String getResponse(String input) {
        try {
            String[] fullCommand = input.split(" ", 2); // Split into command & details
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            return c.commandOutput(); // commandOutput is the text passed to UI.

        } catch (NoSuchElementException e) {
            return ui.showError(e.getMessage());
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Welcomes user.
     */
    public String welcomeUser() {
        return ui.showWelcome();
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String[] fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NoSuchElementException e) {
                ui.showError(e.getMessage());
                return;
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        } // End while loop.
    } // End method.
}
