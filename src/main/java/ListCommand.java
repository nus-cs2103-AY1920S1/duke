/**
 * Represents the Command for printing out all of the tasks in the list.
 * A sub-class of Command.
 */
public class ListCommand extends Command {

    /**
     * Overridden execute method to print out all of the tasks inside the list
     * using the method from TaskList.
     *
     * @param storage Storage object for saving purposes
     * @param tasks   Contains the list of tasks
     * @param ui      Holds Ui printing methods and user input field
     * @param input   User input
     * @return TaskList Message
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui, String input) {
        return tasks.showTaskList();
    }
}
