package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that instructs Duke to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * @param tasks List of tasks
     * @param ui User-Interface
     * @param storage Storage object
     * @return Duke's response.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye! See you again :-)";
    }

    /**
     * Checks if the command is an exit command.
     * @return True
     */
    public boolean checkExit() {
        return true;
    }
}
