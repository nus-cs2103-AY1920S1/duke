package duke.commands;

import duke.DukeException;
import duke.directprocessor.TaskList;
import duke.directprocessor.Ui;
import duke.tasks.Task;

/**
 * This is the specific command to add possible slots to event tasks.
 */
public class SlotCommand extends Command {

    /**
     * The position of the event task to be added with new slot.
     */
    private int position;

    /**
     * The new slot to be added to the event task in the form of a string.
     */
    private String newSlot;

    public SlotCommand(int position, String newSlot) {
        this.position = position;
        this.newSlot = newSlot;
    }

    /**
     * Call the task list to add new slot to the corresponding event task.
     * Call the user interface to generate command execution message as a string.
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
     * Determines whether this command is an exit command or not.
     *
     * @return boolean false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
