package duke.command;

import duke.CommandResponse;
import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents Duke commands that will be processed.
 */
public abstract class Command {
    private boolean isExit;

    Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the Command.
     *
     * @param taskList the TaskList instance Duke is referencing.
     * @param ui the Ui instance handling user-facing interaction.
     * @param storage the Storage instance dealing with hard disk reading/writing.
     */
    public abstract CommandResponse execute(TaskList taskList, Ui ui, Storage storage);
}
