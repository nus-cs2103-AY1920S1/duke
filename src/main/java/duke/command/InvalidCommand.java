package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UiText;

public class InvalidCommand extends Command {
    public InvalidCommand() {
    }

    /**
     * When user key in none of the command key word.
     * @param list TaskList
     * @param ui UiText
     * @param storage Storage
     * @throws DukeException invalid input
     */
    @Override
    public String execute(TaskList list, UiText ui, Storage storage) throws DukeException {
        throw new DukeException("OOPS!! I\'m sorry, but I don\'t know what that means :-(");
    }
}
