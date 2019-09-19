package seedu.duke;

import java.io.IOException;

/**
 * Command is an abstract base class for all commands that the user inputs.
 * A <code>Command</code> object corresponds to a command that starts with "todo", ""deadline",
 * "event", "done", "find" or "bye".
 */
public abstract class Command {
    boolean exit = false;

    /**
     * Constructor of the Command class.
     */
    public Command() {
    }

    /**
     * Abstract method that executes the command.
     *
     * @param list the TaskList object that is handling the arraylist of the datafile
     * @param ui the UserInterface object that handles the interaction with users
     * @param storage the Storage object that stores and handles the path to datafile
     * @return the output string
     * @throws IOException on input error
     */
    public abstract String execute(TaskList list, Ui ui, Storage storage) throws IOException;

}
