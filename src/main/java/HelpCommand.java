/**
 * Simulates a help command of the Duke System.
 * @author Fabian Chia Hup Peng
 */

public class HelpCommand extends Command {

    /**
     * Executes the help command; shows all possible Duke commands.
     * @param taskList The task list for the task to be added to.
     * @param ui The ui which prints the added message.
     * @param storage The storage which deals with the hard drive.
     * @return The string representation of a successful help command.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.stringHelpMessage();
    }

}