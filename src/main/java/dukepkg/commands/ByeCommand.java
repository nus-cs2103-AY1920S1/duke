package dukepkg.commands;

import dukepkg.Storage;

import dukepkg.TaskList;
import dukepkg.Ui;

/**
 * The command used to terminate the programme.
 */
public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.showExitMsg();
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
