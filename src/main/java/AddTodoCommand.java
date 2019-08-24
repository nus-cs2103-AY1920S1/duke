import task.Task;
import task.Todo;

public class AddTodoCommand extends AddCommand {
    public AddTodoCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task task = new Todo(this.description);
        executeAddTask(tasks, ui, task);
    }
}
