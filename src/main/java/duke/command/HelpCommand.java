package duke.command;

import duke.backend.ListManager;
import duke.backend.Storage;
import duke.ui.Ui;

public class HelpCommand extends Command {

    public HelpCommand(String fullCommand, String[] splitCommand) {
        super(fullCommand, splitCommand);
    }

    @Override
    public String execute(ListManager listManager, Ui ui, Storage storage) {
        return listManager.help();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
