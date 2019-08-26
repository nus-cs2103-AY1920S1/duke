package duke.Commands;

import duke.DirectProcessor.TaskList;
import duke.DirectProcessor.Ui;
import duke.DukeException;
import duke.Tasks.Task;

/**
 * This is the Command subclass to delete target task from the target task list.
 * @Extends duke.Commands.Command
 */
public class DeleteCommand extends Command{

    /** The position of the task to delete. Note the first task in the list has position 1 */
    private int position;

    /**
     * Constructor of the class, nothing special.
     * @param position The position of the task to delete.
     */
    public DeleteCommand(int position) {
        this.position = position;
    }

    /**
     * This method deletes the task at the given position in the target task list and let the target
     *     user end print out command message.
     * @param tl The target task list to accept execution.
     * @param ui The target user end to print command information.
     * @throws DukeException If the position is out of bound of the task list.
     */
    @Override
    public void execute(TaskList tl, Ui ui) throws DukeException{
        if (position > Task.getTotalNumber() || position < 1) {
            throw new DukeException("There is no such task in the list. Please input a valid task number.");
        }
        ui.showDeleteMessage(tl.deleteTask(position));
    }

    /**
     * Determines whether this is an exit command.
     * @return boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
