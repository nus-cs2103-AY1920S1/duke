package duke.command;

import duke.backend.ListManager;
import duke.backend.Storage;
import duke.ui.Ui;

public class FindCommand extends Command {

    public FindCommand(String fullCommand, String[] splitCommand) {
        super(fullCommand, splitCommand);
    }

    @Override
    public void execute(ListManager listManager, Ui ui, Storage storage) {
        listManager.find(this.splitCommand[1]);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
