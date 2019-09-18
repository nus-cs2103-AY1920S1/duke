package seedu.duke.commands;

import seedu.duke.exceptions.InvalidArgumentException;
import seedu.duke.storage.TaskList;
import seedu.duke.trackables.Task;
import seedu.duke.ui.Ui;

/**
 * Abstraction of the Delete Command.
 * Contains the {@code taskId} required to identify the task to delete.
 * eg: delete 1
 */
public class DeleteCommand extends Command {

    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId - 1;   // Minus 1 as 1-Index is shown to user while list is 0-Index.
    }

    /**
     * Deletes a task from the {@code TaskList}.
     * @param tasks The current TaskList instance.
     * @throws InvalidArgumentException Thrown when the {@code taskId} provided was out of bounds.
     */
    @Override
    public void execute(TaskList tasks) throws InvalidArgumentException {
        Task taskToRemove = tasks.remove(taskId + 1);
        Ui.printMessages("Noted. I've removed this task:",
            "  " + taskToRemove.toString(),
            "Now you have " + tasks.size() + " tasks in the list.");
    }
}
