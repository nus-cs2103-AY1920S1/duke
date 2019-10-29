package duke.command;

import duke.core.DukeResponder;
import duke.util.Storage;
import duke.util.TaskList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, DukeResponder responder, Storage storage) {
        return responder.getTasksMessage(tasks.filter(this.keyword));
    }

}
