package duke.command;

import duke.core.Storage;
import duke.core.TaskList;

public class InvalidCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
