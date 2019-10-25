package command;

import main.Storage;
import main.TaskList;
import main.UI;
import task.Task;
import task.Todo;

import java.util.ArrayList;

/**
 * A Command to create an instance of a Todo Task. This will add the Todo Task to the TaskList and the Storage file.
 */
public class TodoCommand implements Command {
    private String todoName;

    public TodoCommand(String todoName) {
        this.todoName = todoName;
    }

    /**
     * Executes the Todo command which creates a Todo Task.
     *
     * <p></p>Taking the TaskList and Storage object of the main Duke class as arguments, this command will create a new
     * Deadline Task which will then be added to the TaskList and Storage objects. The UI will also be used to print
     * a newTask message into the console.
     *
     * @param taskList The TaskList from the main Duke object.
     * @param storage The Storage from the main Duke object.
     */
    public String execute(TaskList taskList, Storage storage) {
        ArrayList<Task> list = taskList.getTaskList();

        Task todo = new Todo(todoName);

        taskList.addTaskList(todo);
        storage.writeToFile(list);

        return UI.newTask(list);
    }
}
