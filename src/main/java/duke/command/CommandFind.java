package duke.command;

import duke.exception.DukeException;
import duke.exception.MissingDescriptionException;
import duke.sheet.Sheet;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Encapsulates a command from user input String "find".
 */
public class CommandFind extends Command {

    public CommandFind(String command) {
        super(command);
        super.type = "Find: ";
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) throws DukeException {
        if (this.command.isBlank()) {
            throw new MissingDescriptionException(
                    "> < Sorry, I did not catch your search keyword.");
        }
        sh.find(this.command.trim());
    }

    @Override
    public String toString() {
        return "Find: " + command;
    }
}
