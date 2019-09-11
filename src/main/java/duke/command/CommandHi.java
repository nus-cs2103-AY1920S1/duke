package duke.command;

import duke.sheet.Sheet;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Encapsulates a command from user input String "hi".
 */
public class CommandHi extends Command {

    public CommandHi(String command) {
        super(command);
        super.type = "Hi: ";
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) {
        ui.sayHi();
    }

    @Override
    public String toString() {
        return "Hi: " + command;
    }
}
