package duke.command;

import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword, boolean isExit) {
        super(isExit);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // create a new task list with results containing the keyword.
        TaskList searchList = taskList.find(keyword);

        // inform the user of matching queries (if any)
        ui.showSearchList(searchList);
    }
}
