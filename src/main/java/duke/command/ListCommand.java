package duke.command;

import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class ListCommand extends Command {
    public ListCommand(boolean isExit) {
        super(isExit);
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }
}
