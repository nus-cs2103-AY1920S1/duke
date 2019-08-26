package command;

import core.Storage;
import core.Ui;
import task.TaskList;

public class ExitCommand extends Command {
    public ExitCommand(String commandString) {
        super(commandString);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.setExit(true);
        ui.showBye();
    }
}
