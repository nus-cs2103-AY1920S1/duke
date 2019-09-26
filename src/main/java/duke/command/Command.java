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

    /**
     * Returns if the command executed will exit the program
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the Command
     */
    public abstract String execute(TaskList tasks, Storage storage, String command) throws IOException, DukeException;

}
