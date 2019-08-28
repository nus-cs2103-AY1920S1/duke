import java.io.IOException;

public class DeleteTaskCommand extends Command {
    private int targetIndex;

    public DeleteTaskCommand(int index) {
        targetIndex = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        Task deletedTask = tasks.deleteTask(targetIndex);
        storage.update(tasks);
        ui.print("Noted. I've removed this task:");
        ui.print(deletedTask.toString());
        ui.print(String.format("Now you have %d tasks in the list.", tasks.size()));
    }
}
