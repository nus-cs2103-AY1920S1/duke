package duke.command;

import duke.backend.ListManager;
import duke.backend.Storage;
import duke.ui.Ui;

public abstract class Command {

    String fullCommand;
    String[] splitCommand;

    public Command(String fullCommand, String[] splitCommand) {
        this.fullCommand = fullCommand;
        this.splitCommand = splitCommand;
    }
    public abstract void execute(ListManager listManager, Ui ui, Storage storage);
    public abstract boolean isExit();
}