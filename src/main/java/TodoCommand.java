import java.io.IOException;

public class TodoCommand extends Command {

    private final String description;

    TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(this.description);
        tasks.add(task);
        ui.showTaskAdded(task, tasks);
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            ui.showSaveError();
        }
        return false;
    }

}
