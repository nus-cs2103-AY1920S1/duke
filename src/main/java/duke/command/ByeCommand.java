package duke.command;

import duke.exception.DukeException;
import duke.history.History;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Represents a Bye Command.
 */
public class ByeCommand extends Command {

    /**
     * Executes the Command: Prints out Bye statement and exits the program.
     *
     * @param tasks   current TaskList instance
     * @param storage current Storage instance
     * @param history current History instance
     * @throws DukeException DukeException
     */
    public String execute(TaskList tasks, Storage storage, History history) throws DukeException {
        return "Bye. Hope to see you again soon!";
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
        } else if (obj instanceof ByeCommand) {
            return true;
        }
        return false;
    }
}
