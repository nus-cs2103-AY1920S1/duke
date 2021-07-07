package seedu.duke.logic.command;

import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Defines a Command object that displays information about this Duke program to the user.
 */
public class AboutCommand extends Command {

    /** Executes an AboutCommand object.
     * Displays a message that shows information about this Duke program.
     * @param tasks TaskList that was being used by a client of this command.
     * @param ui Ui with methods that are called to display a message showing information about this Duke program.
     * @param storage Storage that contains saved String representation of the given TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList that is to be displayed is null.";
        ui.showAboutMessage();
    }
    
}
