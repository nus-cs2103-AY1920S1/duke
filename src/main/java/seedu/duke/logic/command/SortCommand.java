package seedu.duke.logic.command;

import seedu.duke.commons.exceptions.DukeException;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Defines a Command object that lists out all Tasks in the TaskList to the user.
 */
public class SortCommand extends Command {

    String sortCriteria;
    
    /** Creates an instance of SortCommand.
     * Stores to attributes of SortCommand relevant information required to create an SortCommand.
     * @param sortCriteria String representation of criteria required to sort Tasks.
     */
    public SortCommand(String sortCriteria) {
        assert !sortCriteria.equals("") : "Task description of an Add command created is empty.";
        this.sortCriteria = sortCriteria.toLowerCase();
    }

    /** Executes a SortCommand object.
     * Displays a message that shows the String representation of the given TaskList sorted by given criteria.
     * @param tasks TaskList that was being used by a client of this command.
     * @param ui Ui with methods that are called to display a message showing the String representation of the
     *           given tasklist that have been sorted by a requested criteria.
     * @param storage Storage that contains saved String representation of the given TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null : "TaskList that is to be displayed is null.";
        if (tasks.size() == 0) {
            ui.showEmptyTaskListMessage();
        } else {
            tasks.sort(sortCriteria);
            ui.showSortedTaskList(tasks);
            storage.save(tasks, ui);
        }
    }
}
