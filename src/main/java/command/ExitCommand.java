package command;

import main.Storage;
import main.TaskList;
import main.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showGoodbye();
        super.canExit();
    }
}
