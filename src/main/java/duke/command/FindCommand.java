package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 *  The <code>FindCommand</code> is created when the user enters <code>"find"</code>. The find command will look through
 *  all the tasks in the list of tasks and find results where the tasks' description matches the keyword.
 */
public class FindCommand implements Command {


    /**
     * The keyword to look out for.
     */
    private String keyword;

    /** Constructs a new find command with the specified keyword.
     * @param keyword the keyword to look out for in the tasks' description
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command. This will look through all the tasks in the list of tasks in {@link TaskList} and show
     * results found in the user interface
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage for the tasks
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showResultsFound(tasks.find(keyword));
    }

    /**
     * Returns <code>true</code> if the command is an exit command and <code>false</code> otherwise.
     * @return <code>false</code>
     */
    public boolean isExit() {
        return false;
    }
}
