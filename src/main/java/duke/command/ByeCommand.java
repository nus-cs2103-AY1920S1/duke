package duke.command;

import duke.task.TaskList;
import duke.util.Ui;

/**
 * ByeCommand class.
 */
public class ByeCommand extends Command {
    /**
     * {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui) {
        ui.print("Bye! Hope to see you again soon!");
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return true;
    }
}
