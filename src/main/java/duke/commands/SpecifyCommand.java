package duke.commands;

import duke.DukeException;
import duke.directprocessor.TaskList;
import duke.directprocessor.Ui;
import duke.tasks.Task;

/**
 * This is the specific command to specify the time of an event task.
 */
public class SpecifyCommand extends Command {

    /**
     * The position of the event task to be specified.
     */
    private int position;

    private String specifiedTime;

    public SpecifyCommand(int position, String specifiedTime) {
        this.position = position;
        this.specifiedTime = specifiedTime;
    }

    /**
     * Call the task list to specify the event time of the corresponding event task.
     *
     * @param tl The target task list to accept the command.
     * @param ui The target user interface to generate the command information as a String.
     * @return The specified event task information as a string.
     * @throws DukeException if the specified time is not in valid form or position exceeds the
     *         length of the task list.
     */
    @Override
    public String execute(TaskList tl, Ui ui) throws DukeException {
        Task specifiedEvent = tl.specifyEventSlot(position, specifiedTime);
        return ui.showSpecifyMessage(specifiedEvent);
    }

    /**
     * Determines whether this command is an exit command or not.
     *
     * @return boolean, false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
