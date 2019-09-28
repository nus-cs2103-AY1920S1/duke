package duke.commands;

import duke.exception.DukeException;
import duke.model.Model;
import duke.parser.Aliases;
import duke.storage.Storage;
import duke.ui.Ui;

public class ViewAliasCommand extends Command {
    private String alias;

    public ViewAliasCommand(String alias) {
        this.alias = alias;
    }

    /**
     * View an alias and the keyword it is mapped to.
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
        ui.showViewAlias(alias, aliases.getKeyword(alias));
    }
}
