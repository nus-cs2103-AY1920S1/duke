import java.io.IOException;

public class SaveCommand extends Command {

    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            storage.save("./Data/duke.txt", tasks.getTasks());
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }

}