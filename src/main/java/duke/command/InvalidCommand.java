package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.UiText;

public class InvalidCommand extends Command {
    public InvalidCommand() {
    }
    @Override
    public void execute(TaskList list, UiText ui, Storage storage) throws DukeException {
        throw new DukeException("\u1F65 OOPS!! I\'m sorry, but I don\'t know what that means :-(");
    }
}
