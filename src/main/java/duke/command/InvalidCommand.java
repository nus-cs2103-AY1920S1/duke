package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

public class InvalidCommand extends Command {

    /**
     * Executes the Invalid Command
     *
     * @param tasks The current tasks loaded
     * @param storage The storage file loaded
     * @param command The command that is being executed
     */
    @Override
    public String execute(TaskList tasks, Storage storage, String command) {
        return ("Invalid Command, try again.");
    }
}
