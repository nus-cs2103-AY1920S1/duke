package duke;

import duke.Commands.Command;
import duke.DirectProcessor.Parser;
import duke.DirectProcessor.Storage;
import duke.DirectProcessor.TaskList;
import duke.DirectProcessor.Ui;
import java.io.IOException;

/**
 * This is the entrance of the whole system. It controls the logic of how the system runs.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * The constructor of the class, to be called at the beginning when the system is activated.
     * It initializes the storage class, user end.
     * The previously saved task list will be reloaded through the storage class and used to initialize the task list.
     * @param fileName The file from which the precious task list is reloaded.
     */
    public Duke(String fileName) {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.reload(fileName));
        } catch(IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch(DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * This is the logic of how the system runs.
     * It first show welcome message to the user, loops the following process before the exit command is given.
     * 1. The ui takes the user's input.
     * 2. The ui draws a line.
     * 3. The Parser processes the user's input and generates the corresponding command.
     * 4. Executes the command and decide if it is an exit command.
     * 5. The ui draws another line
     * 6. The system ends of the command is an exit command, or go back to step 1 otherwise.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.takeCommand();
                ui.drawLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.drawLine();
            }
        }
    }

    /**
     * The main method of class, as well as the entrance method of the whole system.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("taskfile.txt").run();
    }
}
