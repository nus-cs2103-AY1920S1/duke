package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents a done command.
 * A <code>DoneCommand</code> object corresponds to a command to mark a <code>TaskList</code> object
 * in a <code>TaskList</code> as done.
 */
public class DoneCommand extends Command {
    protected int index;

    /**
     * Constructor for <code>DoneCommand</code>.
     * @param index Index of <code>Task</code> object that is to be marked as done in a <code>TaskList</code>.
     */
    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Marks a <code>Task</code> with given index as done in the <code>TaskList</code>.
     * Calls the method in the <code>Ui</code> object to output a message.
     * Calls the method in the <code>Storage</code> object to write all <code>Task</code> objects
     * in the <code>TaskList</code> to the hard disk.
     * @param tasks Instance of <code>TaskList</code> which stores <code>Task</code> objects.
     * @param ui Instance of <code>Ui</code> which handles user input and outputs.
     * @param storage Instance of <code>Storage</code> which stores and loads information to and from the hard disk.
     * @throws DukeException If non-existent index is provided.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task action = tasks.getTask(index);
        if (index + 1 > tasks.getListSize()) {
            throw new DukeException("\u2639 OOPS!!! This item does not exist.");
        }
        action.setDone();
        ui.printDoneMessage(action);
        try {
            storage.writeToHardDisk(tasks);
        } catch (DukeException exception) {
            ui.printException(exception);
        }
    }

    /**
     * Checks if this object is an <code>ExitCommand</code>.
     * @return Whether this command is an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the input index for the <code>DoneCommand</code>.
     * @return Index for command.
     */
    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof DoneCommand) {
            DoneCommand obj = (DoneCommand) o;
            return obj.getIndex() == index;
        } else {
            return false;
        }
    }
}
