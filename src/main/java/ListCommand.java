import java.io.IOException;

/**
 * This class is initiased and its execute method is called whenever you want get all the tasks in the task list.
 */
public class ListCommand extends Command {


    /**
     * Lists all the tasks in the task list
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {

        return ui.list(taskList.list);
    }
}
