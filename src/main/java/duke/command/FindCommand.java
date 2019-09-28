package duke.command;

import duke.backend.ListManager;
import duke.backend.Storage;

public class FindCommand extends Command {

    public FindCommand(String fullCommand, String[] splitCommand) {
        super(fullCommand, splitCommand);
    }

    @Override
    public String execute(ListManager listManager, Storage storage) {
        return listManager.find(this.splitCommand[1]);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
