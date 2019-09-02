package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList);
    }
}
