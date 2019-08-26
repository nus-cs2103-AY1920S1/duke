import java.io.FileNotFoundException;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws FileNotFoundException {
        //tasks.addTask(taskToAdd);
        storage.updateList(tasks.getList());
        ui.printList(tasks.getList());
    }
}
