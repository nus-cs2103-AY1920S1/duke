import java.io.IOException;

public class DeleteCommand extends Command {

    private final int index;

    DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.remove(this.index - 1);
        ui.showDelete(task, tasks);
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            ui.showSaveError();
        }
        return false;
    }

}
