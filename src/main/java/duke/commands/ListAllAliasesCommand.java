package duke.commands;

import duke.exception.DukeException;
import duke.model.Model;
import duke.parser.Aliases;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListAllAliasesCommand extends Command {
    /**
     * List all available aliases and their mapped keywords in Duke.
     * @param model model of the Duke project
     * @param ui an instance of the Ui class
     * @param storage an instance of the storage class
     * @throws DukeException if errors occur when executing the command
     */
    @Override
    public void execute(Model model, Ui ui, Storage storage) throws DukeException {
        Aliases aliases = model.getAliases();
        if (aliases.getAllAliases().isEmpty()) {
            ui.showNoAliases();
        }
        ui.showAllAliases(aliases);
    }
}
