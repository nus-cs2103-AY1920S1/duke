package duke.command;

import duke.exception.DukeException;
import duke.history.History;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Represents an Undo Command.
 */
public class UndoCommand extends Command {

    /**
     * Executes the Command: sets current tasklist and storage to previous iteration.
     *
     * @param tasks   current TaskList instance
     * @param storage current Storage instance
     * @param history current History instance
     * @throws DukeException DukeException
     */
    @Override
    public String execute(TaskList tasks, Storage storage, History history) throws DukeException {
        history.undo();
        return "Previous command has been undone!";
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
        } else {
            return obj instanceof UndoCommand;
        }
    }
}
