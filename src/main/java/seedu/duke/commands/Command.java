package seedu.duke.commands;

import seedu.duke.exceptions.DukeException;
import seedu.duke.storage.TaskList;

/**
 * Represents an abstraction of the Command object.
 * A command object only contains a single abstract {@code execute{TaskList tasks}} method.
 */

public abstract class Command {

    /**
     * Contains the Command specific logic to perform the required operation in Duke.
     * @param tasks The current TaskList instance.
     * @throws DukeException Thrown when an issue arises during execution. Refer to
     *         the specific Command abstractions for the exact exception thrown.
     */
    public abstract String execute(TaskList tasks) throws DukeException;
}
