import java.io.IOException;

public class DoneCommand extends Command {
    final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.get(index).markAsDone();
        storage.syncSaveFile(tasks);
        ui.showDoneTaskMessage(tasks, index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
