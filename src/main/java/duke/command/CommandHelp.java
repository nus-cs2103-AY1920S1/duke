package duke.command;

import duke.sheet.Sheet;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Encapsulates a command from user input String "help".
 */
public class CommandHelp extends Command {

    public static final String COMMAND_WORD = "help";

    public CommandHelp(String command) {
        super(command);
        super.type = "Help: ";
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) {
        ui.showHelp();
    }

}
