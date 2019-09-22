package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a command entered by the user.
 */
public abstract class Command {

    /**
     * Executes the command accordingly and make changes to the TaskList of tasks and
     * the storage.
     * @param tasks TaskList object which represents the list of tasks created by the user.
     * @param storage storage which loads, saves and stores data of user.
     * @return CommandResult result of executing the command.
     * @throws DukeException if user input is invalid.
     */
    public abstract CommandResult execute(TaskList tasks, Storage storage) throws DukeException;

}
