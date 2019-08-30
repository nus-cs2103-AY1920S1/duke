package duke.command;

import duke.component.*;

public class ExitCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) {

        ui.printText("Bye. Hope to see you again soon!");
        ui.closeUi();

    }

    public boolean isExit() {
        return true;
    }
}