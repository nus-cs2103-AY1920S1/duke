package duke.commands;

import duke.directprocessor.TaskList;
import duke.directprocessor.Ui;
import duke.DukeException;

/**
 * This is the specific command to set a target class in the task list as finish.
 */
public class FinishCommand extends Command {

    /** The position of the task to be set as finish. */
    private int position;

    public FinishCommand(int position) {
        this.position = position;
    }

    /**
     * Call the task list to set the corresponding task as done.
     * Call the user interface to generate command execution message as a string.
     *
     * @param tl The target task list to accept the command.
     * @param ui The target user interface to generate the command information as a String.
     * @return The message about the task is finished through string.
     * @throws DukeException if position exceeds the length of the task list.
     */
    @Override
    public String execute(TaskList tl, Ui ui) throws DukeException {
        checkNullPointer(tl, ui);
        if (position > tl.getTotalNumber() || position < 1) {
            throw new DukeException("There is no such task in the list. Please input a valid task number.");
        }
        return ui.showFinishMessage(tl.finishTask(position));
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
