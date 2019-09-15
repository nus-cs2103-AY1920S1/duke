package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    /**
     * Prints out current list stored in TaskList using duke.TaskList.toUiString
     * @param tasks   duke.TaskList
     * @param ui      duke.ui.Ui
     * @param storage duke.Storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show(tasks.toUiString());
    }
}
