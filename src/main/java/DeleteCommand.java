import exception.DukeException;
import tasks.Task;
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
     * @param desc The description for the delete command.
     */
    public DeleteCommand(String desc) {
        super(desc);
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
            if (desc.length() <= 6) {
                throw new DukeException();
            }
            assert (desc.length() > 6);
            String[] descArr = desc.split(" ");
            int num = getTaskNum(descArr);
            if (num >= taskList.getSize()) {
                throw new DukeException();
            }
            assert (num < taskList.getSize());
            Task removed = taskList.removeTask(num);
            storage.updateTaskInFile(taskList.getList());
            return ui.showTaskRemoved(removed, taskList.getSize());
        } catch (DukeException e) {
            return ui.showNoSuchTaskError();
        }
    }
}