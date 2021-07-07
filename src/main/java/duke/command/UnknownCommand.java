package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Handles the command that is not recognised by duke bot.
 */
public class UnknownCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}