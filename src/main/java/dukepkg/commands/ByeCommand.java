package dukepkg.commands;

import dukepkg.TaskList;
import dukepkg.Ui;

/**
 * The command used to terminate the programme.
 */
public class ByeCommand extends Command {

    @Override
    public String execute(TaskList tasklist, Ui ui) {
        this.isExit = true;
        return ui.showExitMsg();
    }

}
