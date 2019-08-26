/**
 * Parent class of all the command objects that deals with user inputs.
 */
public abstract class Command {
    protected String taskDesc;
    protected String timeDesc;
    protected TaskList tasks;
    protected Ui ui;
    protected Storage storage;

    /**
     * Adds the task inputted by the user to the task list.
     * and prints out corresponding response
     * @param tasks holds the list of tasks currently in the program
     * @param ui displays the output from execution
     * @param storage stores the added task to the specified file
     * @throws DukeException if task requirements is not met
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

<<<<<<< HEAD

    /**
     * Determines whether the program stops running.
     * @return boolean value to determine whether the program terminates
     */
=======
>>>>>>> branch-A-CodingStandard
    public abstract boolean isExit();
}

