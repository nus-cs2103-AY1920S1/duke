package dukepkg.commands;

import dukepkg.TaskList;
import dukepkg.Ui;

/**
 * The command used to get user manual.
 */
public class HelpCommand extends Command{
    @Override
    public String execute(TaskList tasklist, Ui ui) {
        return ui.showHelpMsg();
    }
}
