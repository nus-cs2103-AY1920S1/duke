package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class CommandList extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTaskList(tasks);
    }
}
