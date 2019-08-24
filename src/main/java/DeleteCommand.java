import java.io.IOException;

public class DeleteCommand extends Command {
    private int deleteIdx;

    DeleteCommand(int idx) {
        this.deleteIdx = idx;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidTaskException, IOException {
        if (deleteIdx < 1 || deleteIdx > tasks.size()) {
            throw new DukeInvalidTaskException(deleteIdx);
        }
        String result = "Noted. I've removed this task:\n";
        result += "  " + tasks.remove(deleteIdx) + "\n";
        result += "Now you have " + tasks.size() + " tasks in the list.";
        ui.dukePrint(result);
        storage.writeToFile(tasks);
    }

    boolean isExit() {
        return false;
    }
}
