package seedu.duke.logic.command;

import seedu.duke.storage.Storage;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Defines a Command object that lists out all Tasks in the TaskList to the user.
 */
public class FindCommand extends Command {

    String taskString;

    /**
     * Creates an instance of FindCommand.
     * Stores to attributes of FindCommand relevant search information required to create a FindCommand.
     * @param taskString String representation of search description.
     */
    public FindCommand(String taskString) {
        assert !taskString.equals("") : "Keywords for search are empty but no exception is thrown "
                + "when creating a FindCommand object.";
        this.taskString = taskString;
    }

    /** Executes a FindCommand object using information stored in instance attributes.
     * Displays a message that shows the String representation of the TaskList containing search results when called.
     * @param tasks TaskList that was being used by a client of this command.
     * @param ui Ui with methods that are called to display a message showing the String representation of the
     *           given TaskList.
     * @param storage Storage that contains saved String representation of the given TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList searchResults = tasks.find(taskString);
        assert searchResults != null : "TaskList returned by search results is null.";
        ui.showSearchResults(searchResults);
    }

}
