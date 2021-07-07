package duke.command;

import duke.sheet.Sheet;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Encapsulates a command from user input String "list".
 */
public class CommandList extends Command {

    public static final String COMMAND_WORD = "list";

    public CommandList(String command) {
        super(command);
        super.type = "List: ";
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) {
        String list = sh.showList();
        ui.showList(list);
    }

}
