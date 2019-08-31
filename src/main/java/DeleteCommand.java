import java.io.IOException;

public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(String command) {
        try {
            this.taskNumber = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            throw new DukeException("    OOPS!!! Please specify the index of the task to be deleted.");
        }
    }

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
