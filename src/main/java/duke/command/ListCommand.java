package duke.command;

import duke.task.TaskList;

/**
 * ListCommand class.
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     */
    public String[] execute(TaskList taskList) {
        return taskList.printList();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
