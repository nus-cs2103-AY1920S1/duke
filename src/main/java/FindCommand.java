/**
 * Command to search for tasks containing a specific key word/phrase and display those matching tasks.
 */
public class FindCommand extends Command {

    private String searchTerm;

    /**
     * Creates a FindCommand object with the specific key word/phrase assigned.
     *
     * @param searchTerm Key word/phrase to search for.
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Executes the search command.
     *
     * @param tasks Task list to search from.
     * @param ui User interface that assists with printing.
     * @param storage Unused.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTasks(searchTerm, ui);
    }
}
