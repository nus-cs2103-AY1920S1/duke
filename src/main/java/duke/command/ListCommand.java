package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * ListCommand class lists user's tasks.
 *
 * @author scwaterbear
 */
public class ListCommand extends Command {

    /**
     * Class Constructor.
     */
    public ListCommand() {
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.getAllTasks());
    }
}
