import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    public DeleteCommand() {
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String input = ui.getLastCommand();
        String deleteNumber = input.substring(7).split(" ")[0].trim();
        try {
            int taskNumber = Integer.parseInt(deleteNumber) - 1;
            ui.showMessage("Noted. I've removed this task:");
            ui.showMessage(6, tasks.get(taskNumber).toString());
            tasks.remove(taskNumber);
            storage.writeList(tasks);
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException | NumberFormatException err) {
            throw new InvalidDescriptionDukeException("delete", deleteNumber);
        }
    }
}
