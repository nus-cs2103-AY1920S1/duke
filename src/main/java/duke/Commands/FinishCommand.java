package duke.Commands;

import duke.DirectProcessor.TaskList;
import duke.DirectProcessor.Ui;
import duke.DukeException;
import duke.Tasks.Task;

/**
 * This is the Command subclass to set a target class in the task list as finish.
 * @Extends duke.Commands.Command
 */
public class FinishCommand extends Command {

    /** The position of the task to be set as finish. */
    private int position;

    /**
     * The constructor of the class, nothing special.
     * @param position The position of the task to delete.
     */
    public FinishCommand(int position) {
        this.position = position;
    }

    /**
     * This method set the target task in the target task list as finished and let the target user end to
     *     print out command message.
     * @param tl The target task list to accept execution.
     * @param ui The target user end to print command information.
     * @throws DukeException If the position is out of bound of the task list.
     */
    @Override
    public void execute(TaskList tl, Ui ui) throws DukeException{
        if (position > Task.getTotalNumber() || position < 1) {
            throw new DukeException("There is no such task in the list. Please input a valid task number.");
        }
        ui.showFinishMessage(tl.finishTask(position));
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
