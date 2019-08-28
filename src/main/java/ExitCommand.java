import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public boolean executeCommand(TaskList taskList, Storage storage, Ui ui) throws IOException {
        storage.save(taskList);
        ui.showGoodByeScreen();

        return true;
    }
}
