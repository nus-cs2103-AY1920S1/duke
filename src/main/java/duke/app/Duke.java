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
    // Class variables

    // Instance variables.
    private Ui ui;
    private Tasklist tasks;
    private Storage storage;

    private Duke(String filePath) {
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
        new Duke("./data/tasks.txt").run();

    } // End of main.

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
