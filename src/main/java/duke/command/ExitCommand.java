package duke.command;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {

    /**
     * Initialises an ExitCommand.
     */
    public ExitCommand() {

    }

    /**
     * Executes the Exit Command and kills the duke session.
     *
     * @param tasks   The TaskList containing all existing tasks.
     * @param ui      The Ui for printing purposes.
     * @param storage The Storage for saving tasks to file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Checks if it is a bye command.
     *
     * @return A boolean: true if it is a bye command.
     */
    public boolean isExit() {
        return true;
    }
}
