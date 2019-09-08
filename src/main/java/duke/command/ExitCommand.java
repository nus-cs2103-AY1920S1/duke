package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class ExitCommand implements Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
