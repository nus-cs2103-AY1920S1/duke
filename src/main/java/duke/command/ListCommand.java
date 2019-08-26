package duke.command;

import duke.storage.Storage;
import duke.logic.TaskList;
import duke.ui.Ui;

/**
 * List Command.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ListCommand)) {
            return false;
        }

        return true;

    }
}
