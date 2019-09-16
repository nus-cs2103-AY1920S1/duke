package execution.commands;

import exception.DukeException;
import execution.Storage;
import execution.TaskList;
import execution.UI;

/**
 * Represents the characteristics of a Bye Command object.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a ByeCommand.
     *
     * @param bye string value would not matter for this case since we are only interested in the keyword bye.
     */
    public ByeCommand(String bye) {

        super(bye);

    }

    /**
     * Executes a bye command.
     *
     * @param taskList
     * @param ui to set a response from duke.
     * @param storage to store any changes in the storage.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {

        super.execute(taskList, ui, storage);
        checkValidity();

        ui.exit();

    }
}
