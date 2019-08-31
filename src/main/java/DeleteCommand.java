import java.io.IOException;

public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(String command) {
        this.taskNumber = Integer.parseInt(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(taskNumber, ui);
        try {
            storage.store(tasks);
        } catch (IOException e) {
            System.out.println("    ____________________________________________________________\n     OOPS!!! "
                    + e.getMessage() + "\n    ____________________________________________________________\n\n");
        }
    }
}
