package seedu.duke.logic.command;

import seedu.duke.storage.Storage;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Defines a Command object that lists out all Tasks in the TaskList to the user.
 */
public class ListCommand extends Command {

    /** Executes a ListCommand object.
     * Displays a message that shows the String representation of the given TaskList when called.
     * @param tasks TaskList that was being used by a client of this command.
     * @param ui Ui with methods that are called to display a message showing the String representation of the
     *           given tasklist.
     * @param storage Storage that contains saved String representation of the given TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList that is to be displayed is null.";
        if (tasks.size() == 0) {
            ui.showEmptyTaskListMessage();
        } else {
            ui.showTaskList(tasks);
        }
    }
    
}
