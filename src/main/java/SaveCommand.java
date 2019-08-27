import java.io.IOException;

public class SaveCommand extends Command {

    public void execute(TaskList tasks, UI ui) {
        try {
            tasks.save("./Data/duke.txt");
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }

}