package duke.command;

import duke.task.*;
import duke.io.*;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showLine();
        ui.out("Bye. Hope to see you again soon!");
        ui.showLine();
    }
}
