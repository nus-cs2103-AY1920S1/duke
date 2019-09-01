package duke.command;

import duke.task.TaskList;
import duke.util.Ui;

/**
 * ListCommand class.
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui) {
        taskList.printList();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
