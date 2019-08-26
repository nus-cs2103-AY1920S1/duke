import java.io.FileNotFoundException;

public class ExitCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws FileNotFoundException {
        //tasks.addTask(taskToAdd);
        storage.updateList(tasks.getList());
        ui.print("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
