import java.io.IOException;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String command) throws IOException, DukeException {
        isExit = true;
        storage.save(tasks.getList());
        Ui.showBye();
    }
}
