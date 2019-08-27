package duke.command;

import duke.DukeException;
import duke.parser.Task;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.UiText;

public class Command {
    protected String[] command;
    protected boolean isExit = false;

    protected Command() {
    }

    protected Command(String[] msg) {
        this.command = msg;

    }

    protected boolean isExit() {
        return isExit;
    }

    public void execute(TaskList list, UiText ui, Storage storage) throws DukeException {}

}
