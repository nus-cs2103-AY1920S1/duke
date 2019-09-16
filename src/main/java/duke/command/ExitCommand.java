package duke.command;

import duke.model.Task;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.List;

public class ExitCommand implements Command {
    @Override
    public void execute(List<Task> tasks, Ui ui, Storage storage) {
        ui.printBlock("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
