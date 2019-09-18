/**
 * Parent class of all the command objects that deals with user inputs.
 */
public abstract class Command {
    protected TaskList tasks;
    protected Storage storage;

    /**
     * Adds the task inputted by the user to the task list.
     * and prints out corresponding response
     * @param tasks holds the list of tasks currently in the program
     * @param storage stores the added task to the specified file
     * @throws DukeException if task requirements is not met
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Determines whether the program stops running.
     * @return boolean value to determine whether the program terminates
     */
    public boolean isExit() {
        return false;
    }
}

