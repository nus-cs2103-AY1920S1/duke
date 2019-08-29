package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UserInterface;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeException {
        ui.showTaskList(taskList.getTaskNamesIfMatch(this.keyword));
    }
}
