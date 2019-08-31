import java.io.IOException;

public class DoneCommand extends Command {

    private int taskNumber;

    public DoneCommand(String command) {
        try {
            this.taskNumber = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            throw new DukeException("    OOPS!!! Please specify a task number to be marked as done.");
        }
    }

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
            System.out.println("    OOPS!!! " + e.getMessage());
        }
    }
}
