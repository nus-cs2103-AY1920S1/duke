package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Initializes DeleteCommand with index ot the task to be deleted.
     *
     * @param index index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes a task identified by its index from the task list.
     * Prints messages to notify users that the task has been deleted.
     *
     * @param tasks contains task list
     * @param ui deals with interaction with users
     * @param storage deals with loading and saving of task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.showDeleteCommand(tasks, this.index);
            int sizeBeforeRemove = tasks.getTasks().size(); // for Java assert
            tasks.getTasks().remove(this.index);
            assert tasks.getTasks().size() + 1 == sizeBeforeRemove : "incorrect delete";
        } catch (IndexOutOfBoundsException e) {
            ui.showLoadingError("Please enter a valid task number.");
        }
    }
}
