package command;

import main.Helper;
import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * A HelpCommand Object to deal with help requests from the user.
 */
public class HelpCommand extends Command {

    /**
     * Creates a new HelpCommand
     */
    public HelpCommand() {
        super();
    }

    /**
     * Executes the command to help the user print out a list of allowable input formats.
     * @param tasks     The existing task list
     * @param ui        The Ui object which interacts with the current user
     * @param storage   The Storage object which reads and writes to a specified file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Helper helper = new Helper();
        ui.dukeEcho(helper.getCommands());
    }
}
