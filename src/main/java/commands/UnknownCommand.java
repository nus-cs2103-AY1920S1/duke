package commands;

import logic.DukeException;
import logic.DukeList;
import logic.DukeStrings;
import logic.Storage;
import logic.Ui;

public class UnknownCommand implements Command {
    /**
     * Overridden Method to throw an exception due to an unknown command.
     *
     * @param list   list of tasks
     * @param ui      User Interface
     * @param storage File Storage and Management
     * @throws DukeException By default due to unknown command
     */
    @Override
    public void execute(DukeList list, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(DukeStrings.UNKNOWN_INPUT);
    }
}
