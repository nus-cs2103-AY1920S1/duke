package duke.command;

import duke.main.Storage;
import duke.main.Ui;
import duke.main.TaskList;
import duke.exception.DukeException;

/**
 * Represents the various commands given to the bot by the user.
 */
public interface Command {
    public abstract String execute(Storage storage, Ui ui, TaskList tasks) throws DukeException;
}
