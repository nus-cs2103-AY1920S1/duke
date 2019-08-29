/**
 * Represents the deleting of tasks.
 */
public class DeleteCommand extends Command {
    private int taskNum;

    /**
     * Creates a DeleteCommand object that stores the task number to be deleted.
     * @param num Task number to be deleted.
     */
    public DeleteCommand(int num) {
        this.taskNum = num;
    }

    /**
     * Executes the delete command on a TaskList
     *
     * @param tasks TaskList on which the Command should be executed on.
     * @throws DukeException If the task could not be deleted.
     */
    public void execute(TaskList tasks) throws DukeException {
        tasks.deleteTask(taskNum);
    }
}
