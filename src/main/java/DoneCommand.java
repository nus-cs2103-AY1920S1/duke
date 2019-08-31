import java.io.IOException;

public class DoneCommand extends Command {

    private int taskNumber;

    public DoneCommand(String command) {
        this.taskNumber = Integer.parseInt(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.completeTask(taskNumber, ui);
        try {
            storage.store(tasks);
        } catch (IOException e) {
            System.out.println("    OOPS!!! " + e.getMessage());
        }
    }
}
