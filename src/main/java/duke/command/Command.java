package duke.command;

import duke.ui.Ui;
import duke.task.TaskList;
import duke.storage.Storage;

public abstract class Command {

    /**
     * Execute the corresponding commands.
     * @param taskList TaskList object containing the current tasks list.
     * @param ui The Ui object we are currently using.
     * @param storage The Storage object we are currently using.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Checks if the command is an ExitCommand.
     * @return True if it is an ExitCommand, false if otherwise.
     */
    public abstract boolean isExit();

}
