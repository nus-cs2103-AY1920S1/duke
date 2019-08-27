package dukepkg.commands;

import dukepkg.Storage;
import dukepkg.TaskList;
import dukepkg.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.showTaskList(tasklist.tasks);
    }
}
