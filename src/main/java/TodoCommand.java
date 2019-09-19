import exception.DukeException;
import tasks.Todo;
import java.io.IOException;

/**
 * Represents a todo command.
 *
 * @author Michelle Yong
 */
public class TodoCommand extends Command {
    /**
     * Creates a todo command with the description.
     *
     * @param description The description for the todo command.
     */
    public TodoCommand(String description) {
        super(description);
    }

    /**
     * Executes the todo command and shows the todo has been added to the list.
     *
     * @param storage The storage for the file with all the tasks.
     * @param taskList The taskList used.
     * @param ui The User Interface used.
     * @return The message telling user that the todo has been added.
     * @throws IOException If an input or output exception occurred.
     */
    public String execute(Storage storage, TaskList taskList, Ui ui) throws
            IOException {
        try {
            if (description.length() <= 4) {
                throw new DukeException();
            }
            assert (description.length() > 4);
            String task = description.substring(5);
            Todo todo = new Todo(task);
            taskList.addTask(todo);
            storage.appendTaskToFile(todo);
            return ui.showTaskAdded(todo, taskList.getSize());
        } catch (DukeException e) {
            return ui.showTodoError();
        }
    }
}