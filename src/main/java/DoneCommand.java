import java.io.IOException;

public class DoneCommand extends Command {
    private int doneIdx;

    DoneCommand(int idx) {
        this.doneIdx = idx;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidTaskException, IOException {
        if (doneIdx < 1 || doneIdx > tasks.size()) {
            throw new DukeInvalidTaskException(doneIdx);
        }
        tasks.get(doneIdx).markAsDone();
        ui.dukePrint("Nice! I've marked this task as done:\n  " + tasks.get(doneIdx));
        storage.writeToFile(tasks);
    }

    boolean isExit() {
        return false;
    }
}
