package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represent a task has been done command.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Initializes DoneCommand with index of task that is done.
     *
     * @param index index of task that is done
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Sets task identified by its index as done.
     * Prints messages to notify users task is marked as done.
     *
     * @param tasks contains task list
     * @param ui deals with interaction with users
     * @param storage deals with loading and saving of task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.getTasks().get(this.index).setDone(true);
            ui.showDoneCommand(tasks, this.index);
            assert tasks.getTasks().get(this.index).getDoneIcon().equals("\u2713") : "Incorrect done command";
        } catch (IndexOutOfBoundsException e) {
            ui.showLoadingError("Please enter a valid task number.");
        }
    }
}
