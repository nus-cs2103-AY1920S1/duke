package seedu.duke.commands;

import seedu.duke.exceptions.InvalidArgumentException;
import seedu.duke.storage.TaskList;
import seedu.duke.ui.StringStore;

/**
 * Abstraction of the Done Command.
 * Contains the {@code taskID} required to identify the task to mark as 'Done'.
 * eg: done 1
 */
public class DoneCommand extends Command {

    private int taskId;

    public DoneCommand(int taskId) {
        this.taskId = taskId - 1;       // Minus 1 as 1-Index is shown to user while list is 0-Index.
    }

    /**
     * Marks the task in {@code tasks} with index {@code taskId} as 'Done'.
     * @param tasks The current TaskList instance.
     * @throws InvalidArgumentException Thrown when the {@code taskId} provided was out of bounds.
     */
    @Override
    public String execute(TaskList tasks) throws InvalidArgumentException {
        tasks.get(taskId).markAsDone();
        return StringStore.DONE_SUCCESSFUL + "\n  " + tasks.get(taskId).toString();
    }
}
