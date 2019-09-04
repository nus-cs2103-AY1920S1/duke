import java.io.IOException;

/**
 * This class is initiased and its execute method is called whenever you want to add something to the task list.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Intialise this class with a task
     * @param task The Task
     */
    public AddCommand (Task task) {
        this.task = task;
    }


    /**
     * Add the task to the task list
     * @param taskList T
     * @param ui
     * @param storage
     * @throws IOException
     * @return
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {

        taskList.add(task);

        storage.save(taskList.list);

        return ui.add(taskList.list);
    }
}
