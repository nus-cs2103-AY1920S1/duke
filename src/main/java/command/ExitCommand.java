package command;

import main.Storage;
import main.TaskList;
import main.Ui;

public class ExitCommand extends Command {

    public void execute(TaskList task, Ui ui, Storage storage) {
        ui.printBye();
    }
}

