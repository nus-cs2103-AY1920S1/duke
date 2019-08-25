package duke.command;

import duke.command.Command;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }
}
