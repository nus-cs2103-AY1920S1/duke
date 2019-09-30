package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents the command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command on a TaskList to list all Task objects.
     *
     * @param tasks TaskList on which the Command should be executed on.
     * @throws DukeException If a TaskList is empty.
     */
    public String execute(TaskList tasks, Storage storage)throws DukeException {
       return tasks.list();
    }
}
