package duke.command;

import duke.backend.ListManager;
import duke.backend.Storage;
import duke.ui.Ui;

public class AddCommand extends Command {

    public AddCommand(String fullCommand, String[] splitCommand) {
        super(fullCommand, splitCommand);
    }

    @Override
    public void execute(ListManager listManager, Ui ui, Storage storage) {
        listManager.add(this.fullCommand, this.splitCommand);
        ui.successfulAdd(listManager);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
