package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a Bye Command.
 */
public class ByeCommand extends Command {

    /**
     * Method that returns true only if this is an instance of an ExitCommand.
     *
     * @return true
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the Command: Prints out Bye statement and exits the program.
     *
     * @param tasks   current TaskList instance
     * @param ui      current UI instance
     * @param storage current Storage instance
     * @throws DukeException DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printLine("Bye. Hope to see you again soon!");
    }

    /**
     * Method that checks whether this instance is logically equivalent to another Object.
     *
     * @param obj The other object in question.
     * @return true if logically equivalent, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof ByeCommand) {
            return true;
        }
        return false;
    }
}
