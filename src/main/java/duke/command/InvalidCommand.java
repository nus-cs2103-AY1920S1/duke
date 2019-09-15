package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

public class InvalidCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage, String command) {
        return ("Invalid Command, try again.");
    }
}
