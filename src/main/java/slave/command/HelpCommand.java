package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;

/**
 * Command which represents a help command for user to know what commands there are.
 */
public class HelpCommand extends Command {

    /**
     * Constructor for help command.
     */
    public HelpCommand() {
        this.commandType = CommandType.HELP;
    }

    /**
     * Executes by showing the help message from user interface.
     *
     * @param tasks List containing current tasks.
     * @param ui User interface.
     * @return String containing Kappa's formatted dialog.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.showHelpMessage();
    }
}
