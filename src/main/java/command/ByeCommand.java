package command;

import main.Storage;
import main.TaskList;
import main.UI;

public class ByeCommand extends Command {
    public ByeCommand() {

    }

    public void execute(TaskList tl, Storage st) {
        UI.bye();
    }
}
