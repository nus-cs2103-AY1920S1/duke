package duke.command;

import static duke.command.Messages.MSG_ILLEGAL_COMMAND;

import duke.exception.DukeException;
import duke.command.exception.IllegalCommandException;
import duke.sheet.Sheet;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Encapsulates a command from user input String is not recognized as a valid command.
 */
public class IllegalCommand extends Command {

    public IllegalCommand(String command) {
        super(command);
        super.type = "Illegal: ";
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) throws DukeException {
        throw new IllegalCommandException(MSG_ILLEGAL_COMMAND);
    }

}
