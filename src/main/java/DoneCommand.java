import java.io.IOException;

/**
 * Command to mark a task as completed.
 */
public class DoneCommand extends Command {

    private int taskNumber;

    /**
     * Creates a DoneCommand object with the specified task number to be marked as completed assigned.
     *
     * @param command Contains the index of the task to be marked as completed.
     */
    public DoneCommand(String command) {
        try {
            this.taskNumber = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            throw new DukeException("    OOPS!!! Please specify a task number to be marked as done.");
        }
    }

    /**
     * Executes the completion of the task.
     *
     * @param tasks Task list where the task will be marked as completed.
     * @param ui User interface that assists with printing.
     * @param storage Storage to be updated with the task completed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.completeTask(taskNumber, ui);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("    OOPS!!! Your specified task number is out of range.");
        }
        try {
            storage.store(tasks);
        } catch (IOException e) {
            System.err.println("    OOPS!!! " + e.getMessage());
        }
    }
}
