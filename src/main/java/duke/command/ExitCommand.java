package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {

    /**
     * Displays exit message via Ui.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Bye. Hope to see you again soon!");
    }
}
