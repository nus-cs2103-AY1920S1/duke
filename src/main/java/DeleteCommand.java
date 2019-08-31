import java.io.IOException;

/**
 * Command to delete tasks from the task list.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * Creates a DeleteCommand object with the specified task number to be deleted assigned.
     *
     * @param command Contains the index of the task to be deleted.
     */
    public DeleteCommand(String command) {
        try {
            this.taskNumber = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            throw new DukeException("    OOPS!!! Please specify the index of the task to be deleted.");
        }
    }

    /**
     * Executes the delete command.
     *
     * @param tasks Task list where the task should be deleted from.
     * @param ui User interface that assists with printing.
     * @param storage Storage to be updated with the task deleted.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.deleteTask(taskNumber, ui);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("    OOPS!!! Your specified task number is out of range.");
        }
        try {
            storage.store(tasks);
        } catch (IOException e) {
            System.out.println("    OOPS!!! " + e.getMessage());
        }
    }
}
