package duke.command;

import duke.exception.DukeException;
import duke.history.History;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Represents a Delete Command.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the Command: removes current task from TaskList and removes line from storage.
     *
     * @param tasks   current TaskList instance
     * @param storage current Storage instance
     * @throws DukeException DukeException
     */
    public String execute(TaskList tasks, Storage storage, History history) throws DukeException {
        history.addHistoryState();
        Task deleted = tasks.remove(index);
        storage.deleteLine(deleted.storageString());
        return ("Noted. I've removed this task:\n  " + deleted + "\nNow you have "
                + tasks.size() + " tasks in the list.");
    }

    /**
     * Method that checks whether this instance is logically equivalent to another Object.
     *
     * @param obj The other object in question.
     * @return true if logically equivalent, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof DeleteCommand) {
            DeleteCommand other = (DeleteCommand) obj;
            return other.index == this.index;
        }
        return false;
    }
}
