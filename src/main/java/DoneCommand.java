import java.io.IOException;

public class DoneCommand extends Command {

    private final int index;

    DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.get(this.index - 1).markAsDone();
        ui.showDone(tasks.get(this.index - 1));
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            ui.showSaveError();
        }
        return false;
    }

}
