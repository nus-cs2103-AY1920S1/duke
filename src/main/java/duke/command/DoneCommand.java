package duke.command;

import duke.backend.ListManager;
import duke.backend.Storage;

public class DoneCommand extends Command {

    public DoneCommand(String fullCommand, String[] splitCommand) {
        super(fullCommand, splitCommand);
    }

    @Override
    public String execute(ListManager listManager, Storage storage) {
        int index = Integer.parseInt(splitCommand[1]);
        return listManager.done(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

