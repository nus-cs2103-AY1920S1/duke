package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;
import duke.exception.DukeException;

/**
 * Represents an exit Command to stop the program.
 */
public class ExitCommand extends Command {

    /**
     * Instantiates an exit Command.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Updates the storage by writing data to it.
     *
     * @param taskList task list for the command to operate on
     * @param ui ui object to print messages according to the command
     * @param storage storage for the task list to be written
     * @throws DukeException if file is not found
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.writeTo(storage);
        return ui.showBye();
    }
}
