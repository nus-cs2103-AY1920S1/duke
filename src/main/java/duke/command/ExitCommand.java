package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an exit command.
 * An <code>ExitCommand</code> object corresponds to a command to close Duke.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for <code>ExitCommand</code>.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Calls the method in the <code>Ui</code> object to output a message.
     * Calls the method in the <code>Ui</code> object to close the scanner that reads user input.
     * @param tasks Instance of <code>TaskList</code> which stores <code>Task</code> objects.
     * @param ui Instance of <code>Ui</code> which handles user input and outputs.
     * @param storage Instance of <code>Storage</code> which stores and loads information to and from the hard disk.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
        ui.closeScanner();
    }

    /**
     * Checks if this object is an <code>ExitCommand</code>.
     * @return Whether this command is an exit command.
     */
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof ExitCommand) {
            return true;
        } else {
            return false;
        }
    }
}


