package duke.commands;

import duke.core.Storage;
import duke.core.TaskList;
import duke.ui.UiInterface;

/***
 * Abstract command class.
 */
public abstract class Command {
    public boolean isExit;

    /**
     * Class constructor.
     * @param isExit Set to true when command is an exit command
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(Storage storage, TaskList tasks, UiInterface ui);
}
