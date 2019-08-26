package duke.command;

import duke.storage.Storage;
import duke.logic.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Command abstract class inherited by AddCommand, ByeCommand, DeleteCommand, DoneCommand, ListCommand.
 */

public abstract class Command {

    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {

    }

    public boolean isExit() {
        return this.isExit;
    }


}
