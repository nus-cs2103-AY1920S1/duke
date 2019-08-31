package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand implements Command {

    public ExitCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
        storage.save(tasks.save());
    }

    public boolean isExit() {
        return true;
    }

}
