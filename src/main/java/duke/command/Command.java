package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;


import java.io.IOException;

/**
 * Represents a Command that can be executed.
 */
public abstract class Command {
    /**
     * Executes a command based on the type of the Command subclass.
     *
     * @param tasks TaskList on which the Command should be executed on.
     * @throws DukeException If the command failed to be executed.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException, IOException;
}
