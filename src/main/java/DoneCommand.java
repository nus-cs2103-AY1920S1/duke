/**
 * Represents the Command for setting Tasks as completed.
 * A sub-class of Command.
 */
public class DoneCommand extends Command {

    /**
     * Overridden execute method from Command to mark a Task object as completed.
     * The method will check the user input for a valid index and change the selected
     * Task object's isDone boolean variable to true.
     * It will throw an exception if the index is invalid.
     *
     * @param storage Storage object for saving purposes
     * @param tasks   Contains the list of tasks
     * @param ui      Holds Ui printing methods and user input field
     * @param input   User input
     * @return Done Task Message
     * @throws DukeException If taskNo < 0 or > size of list or input is empty
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui, String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("Please specify a task number.");
        }
        int taskNo = Integer.parseInt(input);
        if (taskNo < 0 || taskNo > tasks.getTaskList().size()) {
            throw new DukeException("Task not found.");
        }
        return tasks.setDoneTask(taskNo);
    }
}
