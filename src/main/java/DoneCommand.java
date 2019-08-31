import java.io.IOException;
import java.util.ArrayList;

public class DoneCommand extends Command {
    public DoneCommand() {

    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String input = ui.getLastCommand();
        String doneNumber = input.substring(5);
        try {
            int taskNumber = Integer.parseInt(doneNumber) - 1;
            tasks.complete(taskNumber);
            storage.writeList(tasks);
            ui.showMessage("Nice! I've marked this task as done:");
            ui.showMessage(6, tasks.get(taskNumber).toString());
        } catch (IndexOutOfBoundsException | NumberFormatException err) {
            throw new InvalidDescriptionDukeException("done", doneNumber);
        }
    }
}
