package duke.app;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

import java.util.List;
import java.util.Scanner;

/**
 * Represents the Duke application object.
 */
public class Duke {
    // Class variables

    // Instance variables.

    private Ui ui;
    private Tasklist tasks;

    private Duke() {
        ui = new Ui();
        tasks = new Tasklist();
    }

    public static void main(String[] args) {
        new Duke().run();

    } // End of main.

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String[] fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        } // End while loop.
    } // End method.
}
