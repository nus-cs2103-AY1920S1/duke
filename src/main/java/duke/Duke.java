package duke;

import duke.commands.Command;
import duke.directprocessor.Parser;
import duke.directprocessor.Storage;
import duke.directprocessor.TaskList;
import duke.directprocessor.Ui;

import java.io.IOException;

/**
 * This is the entrance of the whole system. It controls the logic of how the system runs.
 */
public class Duke {

    /**
     * Private instances for the back end.
     */
    private TaskList tasks;
    private Ui ui;

    /**
     * The constructor of the class, to be called at the beginning when the system is activated.
     * It initializes the storage class, user end.
     * The previously saved task list will be reloaded through the storage class and used to initialize the task list.
     */
    Duke() {
        ui = new Ui();
        try {
            tasks = new TaskList(Storage.reload(".taskfile.txt"));
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    String showWelcome() {
        return ui.showWelcome();
    }

    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    boolean canExit(String input) {
        try {
            Command c = Parser.parse(input);
            return c.isExit();
        } catch (DukeException e) {
            return false;
        }
    }
}
