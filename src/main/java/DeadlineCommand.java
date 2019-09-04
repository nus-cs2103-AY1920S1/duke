/**
 * Represents the command that is responsible for creating a deadline Task.
 */
public class DeadlineCommand extends AddCommand {
    /**
     * Creates a deadline command Object.
     */
    public DeadlineCommand(String input) {
        super(input);
    }

    /**
     * Determines whether or should the Duke App should terminate.
     *
     * @return returns false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the construction of a deadline Task.
     *
     * @param tasks   The TaskList of the current Duke App
     * @param storage The Storage unit being used by the Duke app.
     */
    public String execute(TaskList tasks, Storage storage) {
        String command = input.substring(8).trim();
        return addTask(tasks, storage, new DeadlineTask(command));
    }
}