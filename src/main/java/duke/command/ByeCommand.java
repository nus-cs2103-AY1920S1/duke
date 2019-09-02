package duke.command;

import duke.task.Task;
import duke.taskHandler.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class ByeCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws IOException {
        ui.showByeResponse();
        storage.save(tasks);
    }
}
