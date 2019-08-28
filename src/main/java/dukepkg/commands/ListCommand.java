package dukepkg.commands;

import dukepkg.TaskList;
import dukepkg.Ui;

/**
 * The command used to cast contents of the task list onto the screen.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasklist, Ui ui) {
        ui.showTaskList(TaskList.tasks);
    }
}
