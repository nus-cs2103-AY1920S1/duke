package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Message;

/**
 * SortCommand class which sorts the tasks according to a category.
 */
public class SortCommand extends Command {

    /** Category to be sorted by. **/
    private String category;

    /**
     * Initializes a SortCommand.
     * @param category Category to be sorted by.
     */
    public SortCommand(String category) {
        this.category = category;
    }

    @Override
    public CommandResult execute(TaskList taskList) throws DukeException {
        TaskList sortedList = taskList.sort(category);
        return new CommandResult(Message.showSortSuccess(sortedList));
    }

}
