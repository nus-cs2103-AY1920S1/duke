package duke.commands;

import duke.exception.DukeException;
import duke.model.Model;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command {

    /**
     * Perform a command.
     * @param model model of the Duke project
     * @param ui an instance of the Ui class
     * @param storage an instance of the storage class
     * @throws DukeException if errors occur when executing the command
     */
    public abstract void execute(Model model, Ui ui, Storage storage) throws DukeException;
}
