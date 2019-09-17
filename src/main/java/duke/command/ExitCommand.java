package duke.command;

import duke.Saved;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class ExitCommand extends Command {

    /**
     * Relays to the driver class that the user wishes to exit Duke.
     */
    public void execute(TaskList tasks, Ui ui, Saved file) {
        ui.printDuke("Bye. Hope to see you again soon!\n");
    }
}
