package duke.command;

import duke.backend.ListManager;
import duke.backend.Storage;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String fullCommand, String[] splitCommand) {
        super(fullCommand, splitCommand);
    }

    @Override
    public String execute(ListManager listManager, Ui ui, Storage storage) {
        int index = Integer.parseInt(splitCommand[1]);
        return listManager.delete(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

