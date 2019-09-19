package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

/**
 * Represents a command to search a keyword. The <code>FindCommand</code> class
 * inherits from the <code>Command</code> class to represent user instruction
 * to find a task by searching for a keyword.
 */
public class FindCommand extends Command {

    /** A string representation of the keyword for searching. */
    private String keyword;

    /**
     * Constructs a <code>FindCommand</code> object.
     *
     * @param keyword Specifies the keyword for searching.
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    /**
     * Indicates whether this command tells Duke to exit.
     *
     * @return A boolean. True if the command tells Duke to exit, false
     *          otherwise. Since the <code>FindCommand</code> is not a "bye",
     *          the return value is set to false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command with respect to tasks (modifying the task list),
     * UI, and storage (saving tasks in a file in hard disk). GUI version.
     *
     * @param tasks The task list where tasks are stored.
     * @param ui The user interface that interacts with user input.
     * @param storage The <code>Storage</code> object that handles task
     * @return A string that represents the result of this execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showSearchResults(tasks, keyword);
    }
}
