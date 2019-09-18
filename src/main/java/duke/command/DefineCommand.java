package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DefineCommand extends InputCommand {
    public DefineCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] inputSplit = getString().split(" ");
        int len = inputSplit.length;
        if (len != 2) {
            throw new DukeException("☹ OOPS!!! Please follow correct format of \"define [keyword to be replaced] [new keyword]\".");
        }

        String toReplace = inputSplit[0];
        String newKeyword = inputSplit[1];
        Parser.replaceShortcut(toReplace, newKeyword);

        return ui.getReplaceKeywordMessage(toReplace, newKeyword);
    }
}
