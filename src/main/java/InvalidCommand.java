/**
 * The InvalidCommand class that takes care of all unaccepted command words
 passed in by the user.
 */
public class InvalidCommand extends Command {
    /**
     * Constructs and initializes the attributes of a new InvalidCommand object.
     */
    public InvalidCommand() {
        super();
    }

    /**
     * Carries out the command and does the necessary changes and prompts
     user about the change.
     * @param task Current list of tasks.
     * @param ui Ui for user interactions.
     * @param storage Storage for writing of information to text file.
     * @throws DukeException Possibility of throwing a DukeException due to
     an exception occuring in the running of the application.
     */
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
    }
}
