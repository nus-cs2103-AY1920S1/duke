/**
 * Command corresponding to user input "list".
 * Executes the command input by using UI and adds the corresponding tasks to the tasklist.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand object.
     */
    public ListCommand() {

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
        ui.showLine("Here are the tasks in your list:");

        ui.showLine(tasks.printTaskList());
    }

    /**
     * Determines whether the program stops running.
     * @return boolean value false so the program continues to run
     */
    public boolean isExit() {
        return false;
    }

}
