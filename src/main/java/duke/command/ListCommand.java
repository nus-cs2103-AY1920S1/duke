package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage, String command) {
        String message = "";
        message += tasks.output();
        return message;
    }
}
