/**
 * Represents the user input as a Command object.
 */
public abstract class Command {

    protected boolean hasExit;

    /**
     * Default Constructor to set hasExit boolean variable to false.
     * Will be set to true if ExitCommand is called.
     */
    public Command() {
        hasExit = false;
    }

    /**
     * Abstract method for the sub-classes of Command to use. This method will execute
     * differently based on the sub-class called.
     *
     * @param storage Storage object for saving purposes
     * @param tasks   Contains the list of tasks
     * @param ui      Holds Ui printing methods and user input field
     * @param input   User input
     * @throws DukeException If there is an error
     */
    abstract String execute(Storage storage, TaskList tasks, Ui ui, String input) throws DukeException;

    /**
     * Returns boolean variable hasExit for checking exit status.
     *
     * @return hasExit boolean variable
     */
    public boolean isExit() {
        return hasExit;
    }
}
