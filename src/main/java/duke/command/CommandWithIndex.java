package duke.command;

import duke.exception.InvalidTaskIndexDukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public abstract class CommandWithIndex extends Command{
    protected int index;
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
