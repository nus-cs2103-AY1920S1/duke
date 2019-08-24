import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ExitCommand extends Command {

    public ExitCommand() {
        isExit = true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.recordTasks(taskList);
        } catch (IOException e) {
            ui.showSavingError();
        }

    }
}
