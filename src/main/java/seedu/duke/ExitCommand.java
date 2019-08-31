package seedu.duke;

/**
 * Represents a Exit Command.
 * A <code>ExitCommand</code> object corresponds to a command with a description "bye".
 */
public class ExitCommand extends Command {

    /**
     * Constructor of the ExitCommand class.
     */
    public ExitCommand() {
    }

    /**
     * Sets the exit variable to true and prints the exit message.
     *
     * @param list the TaskList object that is handling the arraylist of the datafile
     * @param ui the UserInterface object that handles the interaction with users
     * @param storage the Storage object that stores and handles the path to datafile
     */
    public void execute(TaskList list, Ui ui, Storage storage) {
        exit = true;
        ui.exit();
    }

}
