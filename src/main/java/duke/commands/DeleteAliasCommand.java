package duke.commands;

import duke.exception.DukeException;
import duke.model.Model;
import duke.parser.Aliases;
import duke.storage.Storage;
import duke.ui.Ui;

public class DeleteAliasCommand extends Command {
    private String alias;

    public DeleteAliasCommand(String alias) {
        this.alias = alias;
    }

    /**
     * Delete an alias mapped to a keyword.
     * @param model model of the Duke project
     * @param ui an instance of the Ui class
     * @param storage an instance of the storage class
     * @throws DukeException if errors occur when executing the command
     */
    @Override
    public void execute(Model model, Ui ui, Storage storage) throws DukeException {
        Aliases aliases = model.getAliases();
        if (!aliases.containsAlias(alias)) {
            throw new DukeException("The alias does not exist!");
        }
        String keyword = aliases.getKeyword(alias);
        aliases.removeAlias(alias);
        ui.showDeleteAlias(alias, keyword);
        storage.saveData(model);
    }
}
