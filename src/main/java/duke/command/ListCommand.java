package duke.command;

import duke.core.DukeResponder;
import duke.util.Storage;
import duke.util.TaskList;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, DukeResponder responder, Storage storage) {
        return responder.getTasksMessage(tasks);
    }

}
