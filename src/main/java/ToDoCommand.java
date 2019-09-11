import java.io.IOException;
import java.util.ArrayList;

public class ToDoCommand extends AddCommand {

    public ToDoCommand(String taskDescription) {
        super("todo", taskDescription, false);
    }

    /**
     * Adds ToDo task to list, saves new list to text file.
     * @param taskList AL of tasks
     * @param ui Deals with console input and outputs
     * @param storage Deals with data in hard disk
     * @throws IOException exception
     * @throws DukeException If no description of ToDo task provided
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task newTask = new ToDo(description);
        taskList.addTask(newTask);
        ArrayList<Task> taskArr = taskList.getTaskArr();
        storage.save(taskArr);
        ui.showAddTaskResponse(newTask, taskArr);
    }

}
