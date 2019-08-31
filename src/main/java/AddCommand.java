import java.io.IOException;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task, ui);
        try {
            storage.store(tasks);
        } catch (IOException e) {
            System.out.println("    OOPS!!! " + e.getMessage());
        }
    }
}
