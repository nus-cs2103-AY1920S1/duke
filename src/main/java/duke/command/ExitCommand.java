package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.UiText;

public class ExitCommand extends Command{
    public ExitCommand(String[] msg) {
        super(msg);
        super.isExit = true;
    }

    @Override
    public void execute(TaskList list, UiText ui, Storage storage) throws DukeException {
        ui.leavingMsg();
    }
}
