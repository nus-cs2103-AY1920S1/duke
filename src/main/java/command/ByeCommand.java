package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to end the Duke program.
 *
 */
public class ByeCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }


    @Override
    public String getResponse(TaskList tasklist, Ui ui, Storage storage) {
        return ui.generateResponse("Bye. Hope to see you again soon!",
                "Enter any key to exit the application.");
    }
}
