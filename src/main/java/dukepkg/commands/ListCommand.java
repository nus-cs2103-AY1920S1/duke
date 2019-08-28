package dukepkg.commands;

import dukepkg.Storage;
import dukepkg.TaskList;
import dukepkg.Ui;

/**
 * The command used to cast contents of the tasklist onto the screen.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.showTaskList(TaskList.tasks);
    }
}
