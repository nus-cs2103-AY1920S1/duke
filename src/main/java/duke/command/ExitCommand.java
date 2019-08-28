package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override 
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }
}
