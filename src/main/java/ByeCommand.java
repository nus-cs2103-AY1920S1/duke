public class ByeCommand extends Command {

    /**
     * Constructor for Bye Command.
     *
     * @param action Bye action to shut down duke bot.
     */
    public ByeCommand(String action) {
        super(action);
    }

    /**
     * Prints out bye statement to signal to user
     * that the bot is shutting down.
     *
     * @param tasks Saves tasks into tasklist if task
     *              is present
     * @param ui Prints out messages to show to user.
     * @param storage Stores tasks into file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        return ui.printBye();
    }
}
