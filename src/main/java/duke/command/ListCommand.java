package duke.command;

import duke.task.*;
import duke.io.*;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showLine();
        ui.list(taskList);
        ui.showLine();
    }
}
