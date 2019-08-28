import java.io.IOException;

public class PrintListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        ui.print("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            ui.print(String.format("%d. %s", i, tasks.get(i - 1)));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
