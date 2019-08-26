package duke.command;

import duke.task.Task;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

public class ListTasksCommand extends Command {
    /**
     * List all entries recorded by Duke; print nothing if no entries are present.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui) {
        ui.showList(tasks);
    } // End method.
}
