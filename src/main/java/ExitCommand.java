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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
        ui.closeScanner();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
