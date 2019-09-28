package duke.commands;

import duke.exception.DukeException;
import duke.model.Model;
import duke.parser.Aliases;
import duke.storage.Storage;
import duke.ui.Ui;

public class AddAliasCommand extends Command {
    private String alias;
    private String keyword;

    public AddAliasCommand(String alias, String keyword) {
        this.alias = alias;
        this.keyword = keyword;
    }

    /**
     * Add an alias to a command keyword.
     * @param model model of the Duke project
     * @param ui an instance of the Ui class
     * @param storage an instance of the storage class
     * @throws DukeException if errors occur when executing the command
     */
    @Override
    public void execute(Model model, Ui ui, Storage storage) throws DukeException {
        Aliases aliases = model.getAliases();
        if (aliases.containsAlias(alias)) {
            throw new DukeException(String.format("The alias is already mapped to %s!",
                    aliases.getKeyword(alias)));
        }
        if (!Aliases.KEYWORDS.contains(keyword)) {
            throw new DukeException(String.format("The keyword %s does not exist!", keyword));
        }
        aliases.addAlias(alias, keyword);
        ui.showAddAlias(alias, keyword);
        storage.saveData(model);
    }
}
