import java.io.IOException;

public class MarkAsDoneCommand extends Command {
    private int targetIndex;

    public MarkAsDoneCommand(int index) {
        targetIndex = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        tasks.markTaskAsDone(targetIndex);
        storage.update(tasks);
        ui.print("Nice! I've marked this task as done:");
        ui.print(tasks.get(targetIndex).toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
