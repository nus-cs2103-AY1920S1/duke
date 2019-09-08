package duke.command;

import duke.exception.DukeException;
import duke.history.History;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Represents a Done Command.
 */
public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the Command: Sets task in taskList done, and updates the storage file.
     *
     * @param tasks   current TaskList instance
     * @param storage current Storage instance
     * @param history current History instance
     * @throws DukeException DukeException
     */
    @Override
    public String execute(TaskList tasks, Storage storage, History history) throws DukeException {
        history.addHistoryState();
        String current = tasks.get(index).storageString();
        String doneString = tasks.get(index).setDone();
        String res = tasks.get(index).storageString();
        storage.replaceLine(current, res);
        return doneString;
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
        } else if (obj instanceof DoneCommand) {
            DoneCommand other = (DoneCommand) obj;
            return other.index == this.index;
        }
        return false;
    }
}
