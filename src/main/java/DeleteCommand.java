import java.io.IOException;

/**
 * This class is initiased and its execute method is called whenever you want to delete something from the task list.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Intialise this class with an index
     * @param index The Index
     */
    public DeleteCommand (int index) {
        this.index = index;
    }


    /**
     * Deletes the Task in the task list for the given index
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        Task task = taskList.delete(index);

        storage.save(taskList.list);

        return ui.delete(taskList.list, task);
    }
}
