package duke.command;

import duke.exception.DukeException;
import duke.sheet.Sheet;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Encapsulates a command from user input String "clear".
 */
public class CommandClear extends Command {

    public CommandClear(String command) {
        super(command);
        super.type = "Clear: ";
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) throws DukeException {
        sh.clearList();
        stor.save(sh.getList());
    }
}
