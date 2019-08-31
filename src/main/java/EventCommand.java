/**
 * Represents the command that is responsible for creating a event Task.
 */
public class EventCommand extends AddCommand {
    /**
     * Constructs an event command.
     */
    public EventCommand() {
    }

    /**
     * Determines whether or should the Duke App should terminate.
     * @return returns false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the construction of a event Task.
     * @param tasks The TaskList of the current Duke App
     * @param ui The Ui being used by the Duke App
     * @param storage The Storage unit being used by the Duke app.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String input = ui.getLastCommand();
        String command = input.substring(5).trim();
        addTask(tasks, ui, storage, new EventsTask(command));
    }
}
