/**
 * Command corresponding to user input "bye".
 * Executes the command input by using UI and adds the corresponding tasks to the tasklist.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand object.
     */
    public ExitCommand() {

    }

    /**
     * Adds the program task inputted by the user to the task list.
     * and prints out corresponding response
     * @param tasks holds the list of tasks currently in the program
     * @param ui displays the output from execution
     * @param storage stores the added task to the specified file
     * @throws DukeException if task requirements is not met
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine("Bye. Hope to see you again soon!");
    }

    /**
     * Determines whether the program stops running.
     * @return booleane value true to terminate the program
     */
    public boolean isExit() {
        return true;
    }
}
