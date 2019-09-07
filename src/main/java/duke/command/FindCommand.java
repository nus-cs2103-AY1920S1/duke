package duke.command;

import duke.exception.DukeException;
import duke.history.History;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Represents a Find Command.
 */
public class FindCommand extends Command {
    private String tofind;

    public FindCommand(String s) {
        this.tofind = s;
    }

    /**
     * Executes the Command: Finds the Task(s) in tasks that match the specified string.
     *
     * @param tasks   current TaskList instance
     * @param storage current Storage instance
     * @param history current History instance
     * @throws DukeException DukeException
     */
    @Override
    public String execute(TaskList tasks, Storage storage, History history) throws DukeException {
        TaskList containsList = tasks.find(this.tofind);
        return ("Here are the matching tasks in your list:\n" + containsList.printAllTasks());
    }
}
