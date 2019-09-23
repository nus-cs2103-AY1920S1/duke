package duke.command;

import duke.sheet.Sheet;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Encapsulates a command from user input String "bye".
 */
public class CommandExit extends Command {

    public static final String COMMAND_WORD = "bye";

    public CommandExit(String cmd) {
        super(cmd);
        super.type = "Exit: ";
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
