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
     * @param taskList List containing current tasks.
     * @param ui User interface.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showHelpMessage();
    }
}
