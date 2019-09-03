package duke.command;

import duke.task.TaskList;

/**
 * ByeCommand class.
 */
public class ByeCommand extends Command {
    /**
     * {@inheritDoc}
     */
    public String[] execute(TaskList taskList) {
        return new String[] {"Bye! Hope to see you again soon!"};
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return true;
    }
}
