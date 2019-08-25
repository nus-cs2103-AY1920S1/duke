package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ExitCommand extends Command {
    public void execute(TaskList t, Ui ui, Storage storage) {
        super.exit = true;
        ui.showExitMessage(t.list);
        storage.save(t.list);
    }
}
