/**
 * Represents the command to mark tasks as done.
 */
public class DoneCommand extends Command {

    /** Integer representing number of task to be marked as done. */
    private int taskNum;

    /**
     * Creates a DoneCommand object that stores the task number to be marked as done.
     *
     * @param taskNum Task number that should be marked as done.
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the done command on the TaskList to mark a Task object as done.
     *
     * @param tasks TaskList on which the Command should be executed on.
     * @throws DukeException If the Task does not exist.
     */
    public void execute(TaskList tasks) throws DukeException {
        tasks.markAsDone(taskNum);
    }
}
