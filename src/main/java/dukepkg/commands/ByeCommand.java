package dukepkg.commands;

import dukepkg.TaskList;
import dukepkg.Ui;

/**
 * The command used to terminate the programme.
 */
public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasklist, Ui ui) {
        ui.showExitMsg();
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
