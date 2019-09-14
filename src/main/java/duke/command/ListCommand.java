package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    /**
     * Display all tasks in TaskList via Ui.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show(tasks.toUiString());
    }
}
