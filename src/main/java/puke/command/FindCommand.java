package puke.command;

import puke.storage.Storage;
import puke.task.TaskList;

/**
 * Class representing a command to find items in the task list matching some keyword,
 * and to display those items.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a new FindCommand with the specified keyword.
     *
     * @param keyword The keyword that is being searched for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes this command on the given parameters.
     *
     * @see Command#execute
     */
    public void execute(TaskList tl, ResponseStrings respStrings, Storage storage) {
        respStrings.listFilteredTasks(tl, keyword);
    }
}
