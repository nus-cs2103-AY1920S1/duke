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

    /**
     * Constructor of the class, nothing special.
     *
     * @param position The position of the task to delete.
     */
    public DeleteCommand(int position) {
        this.position = position;
    }

    /**
     * This method deletes the task at the given position in the target task list and let the target
     * user end print out command message.
     *
     * @param tl The task list to accept the command.
     * @param ui The user interface to print command information.
     * @throws DukeException If the position is out of bound of the task list.
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
