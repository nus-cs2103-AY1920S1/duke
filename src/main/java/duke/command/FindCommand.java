package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Message;

/**
 * FindCommand class which finds tasks according to a specified keyword.
 */
public class FindCommand extends Command {

    /** Keyword used to find task. **/
    private String keyword;

    /**
     * Initializes a FindCommand.
     * @param keyword Keyword of the task the user wants to find.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(TaskList taskList) throws DukeException {
        TaskList filteredList = taskList.find(keyword);
        return new CommandResult(Message.showFindSuccess(filteredList));
    }
}
