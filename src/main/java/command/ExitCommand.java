package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
       ui.print("Bye. Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}
