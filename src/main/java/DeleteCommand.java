/**
 * Represents the Command for deleting Tasks for the list.
 * A sub-class of Command.
 */
public class DeleteCommand extends Command {

    /**
     * Overridden execute method from Command to delete a Task object from the list of tasks.
     * The method will check the user input for a valid index and deletes the selected
     * Task object from the list. It will throw an exception if the index is invalid.
     * @param storage Storage object for saving purposes
     * @param tasks Contains the list of tasks
     * @param ui Holds Ui printing methods and user input field
     * @throws DukeException If deletion < 0 or > size of list
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        int deletion = ui.readIndex();
        if (deletion < 0 || deletion > tasks.getTaskList().size()) {
            throw new DukeException("Task not found");
        }
        tasks.deleteTask(deletion);
    }
}
