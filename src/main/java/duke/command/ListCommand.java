package duke.command;

import duke.exception.DukeException;
import duke.exception.EmptyTaskListException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class ListCommand extends Command {
    /**
     * Prints the items in this TaskList.
     *
     * @param taskList The TaskList object passed from Duke.
     * @param ui The Ui object passed from Duke.
     * @param storage The Storage object passed from Duke.
     * @return The response String.
     * @throws DukeException A DukeException custom exception.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.getSize() == 0) {
            throw new EmptyTaskListException("OOPS!!! You have no tasks currently stored in your list!");
        }
        return ui.showList(taskList);
    }
}
