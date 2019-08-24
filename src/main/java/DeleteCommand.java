import java.io.IOException;

public class DeleteCommand extends Command {
    final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.remove(index);
        storage.syncSaveFile(tasks);
        ui.showDeleteTaskMessage(tasks, index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
