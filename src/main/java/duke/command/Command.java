package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;

import java.io.IOException;

public abstract class Command {

    protected boolean isExit;

    public Command() {
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract String execute(TaskList tasks, Storage storage, String command) throws IOException, DukeException;

}
