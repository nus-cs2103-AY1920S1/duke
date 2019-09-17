public class ExitCommand extends Command {

    ExitCommand() {
        super();
    }
    /**
     * Executes the command.
     * Exits the bot.
     * @param tasks to handle the tasks
     * @param ui to get user input
     * @param storage to store tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.closeScanner();
        return ui.exitMessage();
    }

    /**
     * Boolean to exit from program
     * @return true or false depending if we want to stop the program
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
