package command;

import duke.Storage;
import duke.TaskList;
import exception.DukeException;
import exception.DukeInvalidTaskIndexException;

/**
 * Represents a Command which marks a task from the TaskList as done.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Creates a DoneCommand with a given index to mark a task from the TaskList as done.
     * @param index Task index to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Marks a task from the TaskList as done.
     * @param tasks TaskList which stores the list of tasks.
     * @param storage Storage which saves the task into the text file.
     * @throws DukeException DukeException that may arise from invalid inputs.
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (tasks.getSize() == 0) {
            throw new DukeException("You have no tasks to do.");
        } else if (index < 0 || index >= tasks.getSize()) {
            throw new DukeInvalidTaskIndexException("do", tasks.getSize());
        } else {
            tasks.doneTask(index);
            storage.save(tasks);
            return ("Nice! I've marked this task as done:\n  " + tasks.getTask(index));
        }
    }

    public boolean isExit() {
        return false;
    }
}