package duke.command;

import duke.Ui;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents an abstract class Command which executes certain actions based on
 * command type.
 */

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Always returns false unless its a ByeCommand to signal programme hasn't
     * ended.
     * 
     * @return the boolean false
     */

    public boolean isExit() {
        return false;
    }

}
