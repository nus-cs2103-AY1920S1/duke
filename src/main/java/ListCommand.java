import java.io.FileNotFoundException;

public class ListCommand extends Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        //tasks.addTask(taskToAdd);
        storage.updateList(tasks.getList());
        return ui.printList(tasks.getList());
    }
}
