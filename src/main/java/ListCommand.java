import java.io.FileNotFoundException;

public class ListCommand extends Command {

    /**
     * This method is used to call for listing of task
     * in the current list.
     *
     * @return duke lists the tasks
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        //tasks.addTask(taskToAdd);
        storage.updateList(tasks.getList());
        return ui.printList(tasks.getList());
    }
}
