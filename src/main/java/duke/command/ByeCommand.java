package duke.command;

import duke.TaskList;
import duke.ui.TextUi;

public class ByeCommand extends Command {
    public ByeCommand() {
        super.carryOn = false;
    }

    @Override
    public String execute(TaskList taskList) {
        return TextUi.bye();
    }
}
