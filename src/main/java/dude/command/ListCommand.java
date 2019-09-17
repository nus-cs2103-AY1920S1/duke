package dude.command;

import dude.helper.Storage;
import dude.helper.Ui;
import dude.task.TaskList;

/**
 * Displays all existing Tasks in the list.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTaskList(tasks.toArrayList());
    }
}
