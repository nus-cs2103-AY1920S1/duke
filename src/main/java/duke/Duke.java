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
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * The constructor of the class, to be called at the beginning when the system is activated.
     * It initializes the storage class, user end.
     * The previously saved task list will be reloaded through the storage class and used to initialize the task list.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.reload("taskfile.txt"));
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
