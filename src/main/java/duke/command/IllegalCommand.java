package duke.command;

import duke.exception.DukeException;
import duke.exception.IllegalCommandException;
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
        throw new IllegalCommandException("> < Sorry, Nezuko doesn't know what that means.");
    }

    @Override
    public String toString() {
        return "Illegal: " + command;
    }
}
