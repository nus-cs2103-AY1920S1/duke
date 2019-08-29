package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    /**
     * duke.Ui lists all tasks in duke.TaskList.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show(tasks.toUiString());
    }
}
