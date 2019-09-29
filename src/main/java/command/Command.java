package command;

import task.TaskList;
import util.DukeException;
import util.Storage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Command {

    private boolean isExit;
    protected String inputCommand;

    Command() {
        isExit = false;
    }

    public boolean checkExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    /**
     * Abstract method to execute inputCommand.
     *
     * @param taskList the list of tasks
     * @param storage  storage for saving and loading from file
     * @throws DukeException dukeException
     * @throws IOException IOException
     */
    public abstract void executeCommand(TaskList taskList, Storage storage) throws DukeException, IOException;
}
