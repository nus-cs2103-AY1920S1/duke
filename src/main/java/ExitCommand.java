import java.util.List;

public class ExitCommand extends Command {

    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(List<Task> tasks, Ui ui, Storage storage) throws DukeException {
        storage.writeToFile();
        ui.exitMessage();
    }
}
