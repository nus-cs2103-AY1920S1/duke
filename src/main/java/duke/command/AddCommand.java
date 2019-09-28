package duke.command;

import duke.backend.ListManager;
import duke.backend.Storage;

public class AddCommand extends Command {

    public AddCommand(String fullCommand, String[] splitCommand) {
        super(fullCommand, splitCommand);
    }

    @Override
    public String execute(ListManager listManager, Storage storage) {
        return listManager.add(this.fullCommand, this.splitCommand);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
