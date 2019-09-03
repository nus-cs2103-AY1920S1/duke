package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to indicate that the task is done.
 */
public class DoneCommand extends Command {

    private int itemNo;

    /**
     * Constructs a done Command.
     * @param itemNo the index of the task to be indicated done according to the task list
     */
    public DoneCommand(int itemNo) {
        super(false);
        this.itemNo = itemNo;
    }

    /**
     * Marks the task as done.
     * @param taskList task list for the command to operate on
     * @param ui ui object to print messages according to the command
     * @param storage storage for the task list to be written
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.done(itemNo, ui);
    }
}
