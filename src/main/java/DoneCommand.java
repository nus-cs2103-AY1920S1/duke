import java.io.IOException;

public class DoneCommand extends Command {
    int i;

    public DoneCommand(int i) {
        this.i = i;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            String taskMessage = tasks.done(i);
            ui.showDoneMessage(taskMessage);
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }

}