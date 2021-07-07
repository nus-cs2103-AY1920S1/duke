package duke.command;

import duke.exception.InvalidTaskIndexDukeException;
import duke.task.TaskList;

/**
 * An abstract class for commands that requires the task index to execute.
 */
public abstract class CommandWithIndex extends Command {
    protected int index;

    /**
     * Constructs a command with task index.
     * @param index The task's index.
     */
    public CommandWithIndex(int index) {
        this.index = index;
    }

    protected void checkIfIndexIsOutOfBounds(TaskList tasks) throws InvalidTaskIndexDukeException {
        int lastIndex = tasks.getSizeOfTaskList() - 1;
        if (this.index > lastIndex || this.index < 0) {
            throw new InvalidTaskIndexDukeException("Index is out of bounds");
        }
    }
}
