package command;

import parser.Storage;
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
        if (this.index < 0 || this.index >= tasks.getTasks().size()) {
            Command incorrectCommand = new IncorrectCommand();
            incorrectCommand.execute(tasks, ui, storage);
        } else {
            ui.showDeleteCommand(tasks, this.index);
            tasks.getTasks().remove(this.index);
        }
    }
}
