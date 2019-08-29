package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UserInterface;

/**
 * Finds all tasks in the task list that matches given keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Creates a command to find tasks that matches this keyword.
     *
     * @param keyword keyword that is used to find tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes finding of matching tasks on user interface.
     *
     * @param taskList list of tasks.
     * @param ui user interface displaying all tasks that matches this keyword.
     * @param storage local storage of data.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeException {
        ui.showTaskList(taskList.getTaskNamesIfMatch(this.keyword));
    }
}
