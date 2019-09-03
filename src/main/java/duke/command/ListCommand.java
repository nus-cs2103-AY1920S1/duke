package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Creates a ListCommand. It lists all tasks for users.
 */
public class ListCommand extends Command {
    public String execute(TaskList t, Ui ui, Storage storage) {
        return ui.printList(t);
    }
}
