package duke.command;

import duke.storage.Storage;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

public class ListTasksCommand extends Command {
    /**
     * List all entries recorded by Duke; print nothing if no entries are present.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    } // End method.
}
