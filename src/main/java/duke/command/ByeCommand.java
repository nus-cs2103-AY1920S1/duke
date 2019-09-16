package duke.command;

import duke.task.*;
import duke.ui.*;
import duke.storage.*;
import java.io.IOException;

/**
 * This command terminates the application.
 */
public class ByeCommand extends Command{

    /**
     * Stops application from reading anymore user inputs.
     *
     * @return terminated.
     */
    public boolean isTerminated() {
        return true;
    }

    /**
     * Executes the termination of the application.
     * Initiates saving(overwriting) of list of tasks using storage.
     * Show any errors in saving and outputs a final farewell message to the user.
     *
     * @param tasklist existing list of tasks.
     * @param ui user interface to say bye to the user.
     * @param storage saves list of tasks.
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            storage.saveFile(tasklist);
        } catch (IOException e) {
            System.out.println("Unable to save data: " + e.getMessage());
        } finally {
            ui.sendBye();
        }
    }

}
