/**
 * Represents the Command for helping the user use the Chatbot.
 * A sub-class of Command.
 */
public class HelpCommand extends Command {

    /**
     * Overridden execute method from Command to return the help message for the user.
     * The help message details can be found in /data folder.
     *
     * @param storage Storage object for saving purposes
     * @param tasks   Contains the list of tasks
     * @param ui      Holds Ui printing methods and user input field
     * @param input   User input
     * @return Help Message from UI
     * @throws DukeException If help file is unable to be read in showHelp()
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui, String input) throws DukeException {
        return ui.showHelp();
    }
}
