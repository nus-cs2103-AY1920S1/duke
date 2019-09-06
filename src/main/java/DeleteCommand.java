/**
 * Represents the Command for deleting Tasks for the list.
 * A sub-class of Command.
 */
public class DeleteCommand extends Command {

    /**
     * Overridden execute method from Command to delete a Task object from the list of tasks.
     * The method will check the user input for a valid index and deletes the selected
     * Task object from the list. It will throw an exception if the index is invalid.
     *
     * @param storage Storage object for saving purposes
     * @param tasks   Contains the list of tasks
     * @param ui      Holds Ui printing methods and user input field
     * @param input   User input
     * @return Delete Task Message
     * @throws DukeException If deletion < 0 or > size of list or input is empty
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui, String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("Please specify a task number.");
        }
        int deletion = Integer.parseInt(input);
        if (deletion < 0 || deletion > tasks.getTaskList().size()) {
            throw new DukeException("Task not found.");
        }
        return tasks.deleteTask(deletion);
    }
}
