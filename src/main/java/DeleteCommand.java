import java.io.IOException;

/**
 * Represents a delete command.
 *
 * @author Michelle Yong
 */
public class DeleteCommand extends Command {
    /**
     * Creates a delete command with the description.
     *
     * @param description The description for the delete command.
     */
    public DeleteCommand(String description) {
        super(description);
    }

    /**
     * Executes the delete command and shows that the task has been deleted.
     *
     * @param storage The storage for the file with all the tasks.
     * @param taskList The taskList used.
     * @param ui The User Interface used.
     * @return The message telling user that the task has been deleted.
     * @throws IOException If an input or output exception occurred.
     */
    public String execute(Storage storage, TaskList taskList, Ui ui)
            throws IOException {
        try {
            if (description.length() <= 6) {
                throw new DukeException();
            }
            assert (description.length() > 6);
            String[] descArr = description.split(" ");
            int taskNum = getTaskNum(descArr);
            if (taskNum >= taskList.getSize()) {
                throw new DukeException();
            }
            assert (taskNum < taskList.getSize());
            Task removedTask = taskList.removeTask(taskNum);
            storage.updateTaskInFile(taskList.getList());
            return ui.showTaskRemoved(removedTask, taskList.getSize());
        } catch (DukeException e) {
            return ui.showNoSuchTaskError();
        }
    }
}