/**
 * An abstract class representing a command.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param tasks a list task to work on.
     * @param ui an user interface to show messages.
     * @throws IllegalIndexOfTaskException If the index of the task is out of range.
     */
    public abstract void execute(TaskList tasks, Ui ui) throws IllegalIndexOfTaskException;
}
