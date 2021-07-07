package duke.command;

import static duke.command.Messages.MSG_MISSING_KEYWORD;

import duke.exception.DukeException;
import duke.command.exception.MissingDescriptionException;
import duke.sheet.Sheet;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Encapsulates a command from user input String "find".
 */
public class CommandFind extends Command {

    public static final String COMMAND_WORD = "find";

    public CommandFind(String command) {
        super(command);
        super.type = "Find: ";
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) throws DukeException {
        if (this.command.isBlank()) {
            throw new MissingDescriptionException(MSG_MISSING_KEYWORD);
        }
        String found = sh.find(this.command.trim());
        ui.showSearch(found);
    }

}
