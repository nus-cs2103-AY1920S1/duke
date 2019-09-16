import exception.DukeException;
import tasks.Task;
import tasks.Todo;

import java.io.IOException;

public class TodoCommand extends Command {
    public TodoCommand(String desc) {
        super(desc);
    }

    /**
     * Execute the command and stores the todo added into the task list.
     *
     * @param storage The storage class with the file which the todo is loaded to.
     * @param taskList The class with list of task currently.
     * @return The todo that is added, showing the number of tasks in the list currently.
     * @throws IOException If an input or output exception occurred.
     */
    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException {
        try {
            if (desc.length() <= 4) {
                throw new DukeException();
            }
            String task = desc.substring(5);
            Todo todo = new Todo(task);
            taskList.addTask(todo);
            storage.appendTaskToFile(todo);
            return ui.showTaskAdded(todo, taskList.getSize());
        } catch (DukeException e) {
            return ui.showTodoError();
        }
    }
}
