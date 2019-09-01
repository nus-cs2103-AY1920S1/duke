package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String command, String keyword, Task pendingTask) {
        super(command, pendingTask);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ArrayList<String> listFound = list.find(keyword);
        ui.showMatchingTaskList(listFound);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
