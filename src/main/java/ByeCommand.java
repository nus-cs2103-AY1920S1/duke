/**
 * Represents a bye command that can be executed.
 */
public class ByeCommand extends Command {
    /**
     * Constructs a ByeCommand Object.
     */
    public ByeCommand() {
    }

    /**
     * Returns whether or not the command is the exit command.
     * @return always returns true.
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the bye command. utilizes the given TaskList, Ui and Storage.
     * @param tasks The TaskList of the current Duke App
     * @param ui The Ui being used by the Duke App
     * @param storage The Storage unit being used by the Duke app.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye! See you again soon!!");
    }
}
