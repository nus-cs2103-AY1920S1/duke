package duke.command;

import duke.backend.ListManager;
import duke.backend.Storage;


public abstract class Command {

    String fullCommand;
    String[] splitCommand;

    public Command(String fullCommand, String[] splitCommand) {
        this.fullCommand = fullCommand;
        this.splitCommand = splitCommand;
    }

    public abstract String execute(ListManager listManager, Storage storage);

    public abstract boolean isExit();
}