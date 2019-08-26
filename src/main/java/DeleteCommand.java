import java.io.FileNotFoundException;

public class DeleteCommand extends Command {
    int taskNumToDelete;
    public DeleteCommand(int taskNumToDelete) {
        this.taskNumToDelete = taskNumToDelete;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FileNotFoundException {
        Task t = tasks.deleteTask(taskNumToDelete);
        storage.updateList(tasks.getList());
        ui.print("Noted. I've removed this task: \n" + t + "\nNow you have "
                + tasks.getList().size() + " tasks in the list.");
    }
}
