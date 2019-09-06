package duke.commands;

import duke.DukeException;
import duke.directprocessor.TaskList;
import duke.directprocessor.Ui;
import duke.tasks.Task;

/**
 * This is the specific command to add possible slots to event tasks.
 */
public class SlotCommand extends Command {

    private int position;
    private String newSlot;

    /**
     * The constructor of the command, nothing special.
     *
     * @param position The position of the target event in the task list.
     * @param newSlot The new slot to be added to the target event.
     */
    public SlotCommand(int position, String newSlot) {
        this.position = position;
        this.newSlot = newSlot;
    }

    /**
     * This method adds a new possible slot to the target event.
     *
     * @param tl The target task list to accept execution.
     * @param ui The target user end to print command information.
     * @return the modified task information as s string.
     * @throws DukeException If the position exceeds the task list size or the new slot time is in invalid form.
     */
    @Override
    public String execute(TaskList tl, Ui ui) throws DukeException {
        Task modifiedEvent = tl.addSlotToEvent(position, newSlot);
        return ui.showSlotMessage(modifiedEvent);
    }

    /**
     * determines whether this command is an exit command or not.
     *
     * @return boolean false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
