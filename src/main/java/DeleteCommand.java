import java.io.IOException;

public class DeleteCommand extends Command {
    int i;

    public DeleteCommand(int i) {
        this.i = i;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            String taskMessage = tasks.delete(i);
            ui.showDeleteMessage(taskMessage, tasks.getTasksSize());
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }

}