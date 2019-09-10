package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UiText;
import java.io.IOException;

public class ExitCommand extends Command {
    public ExitCommand(String[] msg) {
        super(msg);
        super.isExit = true;
    }

    @Override
    public String execute(TaskList list, UiText ui, Storage storage) {
        try {
            storage.updateFile(list.getList());
            return UiText.leavingMsg();
        } catch (IOException e) {
            //error msg
            return UiText.unableToWriteFileError();
        }
    }
}
