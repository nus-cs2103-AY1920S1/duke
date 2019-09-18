package duke.command;

import duke.Saved;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class ExitCommand extends Command {

    /**
     * Relays to the driver class that the user wishes to exit Duke.
     */
    public String execute(TaskList tasks, Ui ui) {
        return ui.showBye();
    }
}
