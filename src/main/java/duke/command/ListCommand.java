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

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks.getAllTasks());
    }
}
