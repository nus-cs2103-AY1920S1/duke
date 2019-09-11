package duke.command;

import duke.data.Storage;
import duke.object.TaskList;
import duke.user.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.confirmDelete(tasks.delete(idx));
        storage.saveTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
