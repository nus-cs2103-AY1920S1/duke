package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents a delete command.
 * A <code>DeleteCommand</code> object corresponds to a command to remove a <code>TaskList</code> object.
 * from a <code>TaskList</code>.
 */
public class DeleteCommand extends Command {
    protected int index;

    /**
     * Constructor for <code>DeleteCommand</code>.
     * @param index Index of <code>Task</code> that is to be deleted in a <code>TaskList</code>.
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Deletes <code>Task</code> with given index from the <code>TaskList</code>.
     * Calls the method in the <code>Ui</code> object to output a message.
     * Calls the method in the <code>Storage</code> object to write all <code>Task</code> objects
     * in the <code>TaskList</code> to the hard disk.
     * @param tasks Instance of <code>TaskList</code> which stores <code>Task</code> objects.
     * @param ui Instance of <code>Ui</code> which handles user input and outputs.
     * @param storage Instance of <code>Storage</code> which stores and loads information to and from the hard disk.
     * @throws DukeException If non-existent index is provided.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(index);
        if (index + 1 > tasks.getListSize()) {
            throw new DukeException("\u2639 OOPS!!! This item does not exist.");
        }
        tasks.deleteTask(index);
        int numberOfTasks = tasks.getListSize();
        ui.printDeleteMessage(task, numberOfTasks);
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
     * Returns the input index for the <code>DeleteCommand</code>.
     * @return Index for command.
     */
    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof DeleteCommand) {
            DeleteCommand obj = (DeleteCommand) o;
            return obj.getIndex() == index;
        } else {
            return false;
        }
    }
}

