package q.command;

import q.main.Storage;
import q.main.TaskList;
import q.main.Ui;

/**
 * Handles finding.
 */
public class FindCommand extends Command {
    private String searchTerm;

    /**
     * Constructs a FindCommand.
     *
     * @param searchTerm The search term.
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Finds a list of Tasks containing the searchTerm.
     *
     * @param tasks   The main TaskList.
     * @param ui      The User Interface being used.
     * @param storage The Storage being used.
     * @return A TaskList of the Tasks containing the searchTerm.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = tasks.find(searchTerm);
        if (foundTasks.isEmpty()) {
            return ui.showNoFindTasks();
        }
        return ui.showFindTaskList(foundTasks);
    }
}
