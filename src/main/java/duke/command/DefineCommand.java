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
        int indexToReplace = -1;

        String[] inputSplit = getString().split(" ");
        int len = inputSplit.length;
        if (len != 2) {
            throw new DukeException("☹ OOPS!!! Please follow correct format of \"define [keyword to be replaced] [new keyword]\".");
        }

        String toReplace = inputSplit[0];
        String newKeyword = inputSplit[1];
        String[] shortcuts = Parser.shortcuts;
        for (int i = 0; i < shortcuts.length; ++i) {
            String current = shortcuts[i];
            if (current.equals(newKeyword)) {
                throw new DukeException("☹ OOPS!!! Please enter a new keyword that is not in use.");
            }
            if (current.equals(toReplace)) {
                indexToReplace = i;
            }
        }
        if (indexToReplace != -1) {
            shortcuts[indexToReplace] = newKeyword;
            return ui.getReplaceKeywordMessage(toReplace, newKeyword);
        }
        throw new DukeException("☹ OOPS!!! Please enter a valid shortcut to be replaced.");
    }
}
