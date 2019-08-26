package duke;

/**
 * Represents a Command object. A <code>Command</code> object corresponds to
 * an input from user e.g., bye
 */
public abstract class Command {

    /**
     * Executes the command, accesses the task list.
     * User interface prints out message to user.
     * Throws exception if task is not found.
     *
     * @param tasks Contains the task list .
     * @param ui Deals with interactions with the user.
     * @param storage Deals with loading tasks from the file and saving tasks in the file.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns false for exit status.
     * If exit status is false, Duke continues operating.
     */
    public abstract boolean isExit();
}
