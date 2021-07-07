public class HelpCommand extends Command {
    /**
     * Constructor for a HelpCommand object
     */
    public HelpCommand() {
        super();
    }

    /**
     * Executes the command.
     * Shows help info to the user.
     * @param tasks to handle the tasks
     * @param ui to get user input
     * @param storage to store tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.helpMessage(storage);
    }

    /**
     * Boolean to exit from program
     * @return true or false depending if we want to stop the program
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
