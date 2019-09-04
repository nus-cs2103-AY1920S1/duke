/**
 * Represents a bye command that can be executed.
 */
public class ByeCommand extends Command {
    /**
     * Constructs a ByeCommand Object.
     */
    public ByeCommand(String input) {
        super.input = input;
    }

    /**
     * Returns whether or not the command is the exit command.
     *
     * @return always returns true.
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the bye command. utilizes the given TaskList and Storage.
     * @param tasks   The TaskList of the current Duke App.
     * @param storage The Storage unit being used by the Duke app.
     */
    public String execute(TaskList tasks, Storage storage) {
        return "Bye! See you again soon!!";
    }
}
