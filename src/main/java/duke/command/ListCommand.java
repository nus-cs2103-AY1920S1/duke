package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Create a ListCommand. It lists all tasks for users.
 */
public class ListCommand extends Command {
    public void execute(TaskList t, Ui ui, Storage storage) {
        ui.printList(t);
    }
}
