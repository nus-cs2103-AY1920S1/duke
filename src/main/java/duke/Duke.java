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

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
