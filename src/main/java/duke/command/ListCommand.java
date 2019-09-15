package duke.command;

import duke.exception.DukeException;
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
        return ui.showList(taskList);
    }
}
