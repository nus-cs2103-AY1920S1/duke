package duke.command;

import duke.exception.DukeException;
import duke.history.History;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Represents a List Command.
 */
public class ListCommand extends Command {

    /**
     * Executes the Command: Prints out tasks in TaskList.
     *
     * @param tasks   current TaskList instance
     * @param storage current Storage instance
     * @param history current History instance
     * @throws DukeException DukeException
     */
    public String execute(TaskList tasks, Storage storage, History history) throws DukeException {
        return tasks.printAllTasks();
    }

    /**
     * Method that checks whether this instance is logically equivalent to another Object.
     *
     * @param obj The other object in question.
     * @return true if logically equivalent, false otherwise
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            return obj instanceof ListCommand;
        }
    }
}
