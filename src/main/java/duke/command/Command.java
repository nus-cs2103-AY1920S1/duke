package duke.command;

/**
 * Encapsulates a Command object that is able to represent different types of command.
 */

import duke.core.Storage;
import duke.core.TaskList;

import java.io.IOException;

public class Command {

    String fullCommand;

    /**
     * Creates a new Command object containing the full, valid input command.
     * @param fullCommand String of full, valid input command.
     */
    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes actions according different command type.
     * @param tasks TaskList object containing a list of existing tasks.
     * @param storage the storage object that deals with saving and loading task lists.
     * @throws IOException thrown when no file found.
     */
    public String execute(TaskList tasks, Storage storage) throws IOException {
        return "";
    }
}
