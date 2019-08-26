package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printOutput(taskList.list());
    }
}
