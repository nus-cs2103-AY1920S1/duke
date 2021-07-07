package seedu.duke.logic.command;

import seedu.duke.storage.Storage;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Defines a Command object that instructs the program to exit.
 */
public class ExitCommand extends Command {

    /** Executes an ExitCommand object.
     * Displays a message indicating exiting of the program before the program exits.
     * @param tasks TaskList that was being used by a client of this command.
     * @param ui Ui with methods that are called to display a message before the program exits.
     * @param storage Storage that contains saved String representation of the given TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
        assert isExit() == true : "An exit command is not instructing the program to exit.";
    }

    /**
     * Returns information that the ExitCommand instructs the program to exit.
     * Defines that the ExitCommand causes the program to exit.
     * @return A boolean representing that the ExitCommand instructs the program to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
