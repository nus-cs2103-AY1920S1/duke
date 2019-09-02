package duke.command;

import duke.exception.IllegalIndexOfTaskException;
import duke.task.TaskList;

/**
 * An abstract class representing a command.
 */
public abstract class Command {
    /**
     * Returns the result of executing the command.
     * @param tasks a list task to work on.
     * @return the result of executing the command.
     * @throws IllegalIndexOfTaskException If the index of the task is out of range.
     */
    public abstract CommandResult execute(TaskList tasks) throws IllegalIndexOfTaskException;
}
