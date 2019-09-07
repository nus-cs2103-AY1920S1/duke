import java.io.IOException;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.writeToFile(tasks);
            ui.showExit();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
