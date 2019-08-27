import java.io.IOException;

public class IllegalCommand extends Command {

    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            ui.showIllegalCommandMessage();
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }

}