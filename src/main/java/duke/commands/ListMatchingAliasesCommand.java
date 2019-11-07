package duke.commands;

import java.util.List;

import duke.exception.DukeException;
import duke.model.Model;
import duke.parser.Aliases;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListMatchingAliasesCommand extends Command {
    private String keyword;

    public ListMatchingAliasesCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * View all aliases mapped to a keyword.
     * @param model model of the Duke project
     * @param ui an instance of the Ui class
     * @param storage an instance of the storage class
     * @throws DukeException if errors occur when executing the command
     */
    @Override
    public void execute(Model model, Ui ui, Storage storage) throws DukeException {
        Aliases aliases = model.getAliases();
        if (!Aliases.KEYWORDS.contains(keyword)) {
            throw new DukeException("The keyword does not exist!");
        }
        List<String> matchingAliases = aliases.getAliases(keyword);
        if (matchingAliases.isEmpty()) {
            ui.showNoMatchingAliases(keyword);
        } else {
            ui.showMatchingAliases(matchingAliases, keyword);
        }
    }
}
