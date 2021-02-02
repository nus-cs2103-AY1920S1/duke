package duke.commands;

import duke.directprocessor.TaskList;
import duke.directprocessor.Ui;
import duke.DukeException;
import duke.tasks.Task;

/**
 * This is the specific command to delete the target task in the target list.
 */
public class DeleteCommand extends Command {

    /**
     * The position of the task to delete, which is the order of the target task in the task list.
     * Note the first task in the list has position 1
     */
    private int position;

    public DeleteCommand(int position) {
        this.position = position;
    }

    /**
     * Call the task list to delete the corresponding task.
     * Call the user interface to generate command execution message as a string.
     *
     * @param tl The target task list to accept the command.
     * @param ui The target user interface to generate the command information as a String.
     * @return The task message of the deleted task.
     * @throws DukeException when position exceeds the length of the task list.
     */
    @Override
    public String execute(TaskList tl, Ui ui) throws DukeException {
        checkNullPointer(tl, ui);
        if (position > tl.getTotalNumber() || position < 1) {
            throw new DukeException("There is no such task in the list. Please input a valid task number.");
        }
        Task deletedTask = tl.deleteTask(position);
        int totalTaskNumber = tl.getTotalNumber();
        return ui.showDeleteMessage(deletedTask, totalTaskNumber);
    }

    /**
     * Determines whether this is an exit command.
     *
     * @return boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
