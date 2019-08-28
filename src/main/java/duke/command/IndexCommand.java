package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class IndexCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}