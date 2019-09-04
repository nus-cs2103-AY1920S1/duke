/**
 * Represents the command that is responsible for creating a TodoTask.
 */
public class ToDoCommand extends AddCommand {
    /**
     * Constructs a ToDoCommand.
     */
    public ToDoCommand(String input) {
        super(input);
    }

    /**
     * Determines whether or should the Duke App should terminate.
     * @return returns false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the construction of a TodoTask.
     * @param tasks   The TaskList of the current Duke App.
     * @param storage The Storage unit being used by the Duke app.
     */
    public String execute(TaskList tasks, Storage storage) {
        String command = input.substring(4).trim();
        return addTask(tasks, storage, new ToDoTask(command));
    }
}
