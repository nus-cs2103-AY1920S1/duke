package duke.command;

import duke.exception.IllegalIndexOfTaskException;
import duke.task.TaskList;

/**
 * An abstract class representing a command.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param tasks a list task to work on.
     * @throws IllegalIndexOfTaskException If the index of the task is out of range.
     * @return
     */
    public abstract CommandResult execute(TaskList tasks) throws IllegalIndexOfTaskException;
}
