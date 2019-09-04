package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

public abstract class Command {
    protected String input;

    public Command(String message) {
        this.input = message;
    }

    public String getMessage() {
        return input;
    }

    public abstract void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception;
}
