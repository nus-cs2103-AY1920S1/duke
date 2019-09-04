/**
 * Represents the command that is responsible for creating a event Task.
 */
public class EventCommand extends AddCommand {
    /**
     * Constructs an event command.
     */
    public EventCommand(String input) {
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
     * Executes the construction of a event Task.
     *
     * @param tasks   The TaskList of the current Duke App
     * @param storage The Storage unit being used by the Duke app.
     */
    public String execute(TaskList tasks, Storage storage) {
        String command = input.substring(5).trim();
        return addTask(tasks, storage, new EventsTask(command));
    }
}
