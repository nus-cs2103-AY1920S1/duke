import java.io.FileNotFoundException;

public class AddCommand extends Command {
    Task taskToAdd;
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FileNotFoundException {
        tasks.addTask(taskToAdd);
        storage.updateList(tasks.getList());
        ui.print("Got it. I've added this task: \n" + taskToAdd + "\nNow you have "
                + tasks.getList().size() + " tasks in the list.");
    }
}
