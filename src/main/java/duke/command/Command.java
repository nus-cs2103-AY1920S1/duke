package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;

/**
 * Abstract Command class can be extended to create sub commands which are executable.
 */
public abstract class Command {

    /**
     * Drives the system by processing the given commands and doing the corresponding actions.
     * @param taskList TaskList which carries out tasks which edit the task list.
     * @throws DukeException Exception is thrown when invalid or incomplete commands are given.
     */
    public abstract CommandResult execute(TaskList taskList) throws DukeException;
}
