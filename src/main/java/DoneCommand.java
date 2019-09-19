import exception.DukeException;
import tasks.Task;
import java.io.IOException;

/**
 * Represents a done command.
 *
 * @author Michelle Yong
 */
public class DoneCommand extends Command {
    /**
     * Creates a done command with the description.
     *
     * @param description The description for the done command.
     */
    public DoneCommand(String description) {
        super(description);
    }

    /**
     * Executes the done command and shows that the task has been marked done.
     *
     * @param storage The storage for the file with all the tasks.
     * @param taskList The taskList used.
     * @param ui The User Interface used.
     * @return The message telling user that the task has been marked done.
     * @throws IOException If an input or output exception occurred.
     * @throws AssertionError If an assertion error occurred.
     */
    public String execute(Storage storage, TaskList taskList, Ui ui) throws
            IOException, AssertionError {
        try {
            if (description.length() <= 4) {
                throw new DukeException();
            }
            assert (description.length() > 4);
            String[] descArr = description.split(" ");
            int taskNum = getTaskNum(descArr);
            if (taskNum >= taskList.getSize()) {
                throw new DukeException();
            }
            assert (taskNum < taskList.getSize());
            Task task = taskList.markTaskAsDone(taskNum);
            assert task.getIsDone();
            storage.updateTaskInFile(taskList.getList());
            return ui.showTaskDone(task);
        } catch (DukeException e) {
            return ui.showNoSuchTaskError();
        }
    }
}