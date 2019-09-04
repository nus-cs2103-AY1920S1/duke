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
     * @param storage stores the added task to the specified file
     * @return String to be displayed as Duke response in GUI
     */
    public String execute(TaskList tasks, Storage storage) {
        String printable = "Here are the tasks in your list:" + "\n";

        printable += tasks.printTaskList();
        return printable;
    }
}
