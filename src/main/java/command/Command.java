package command;

import duke.Storage;
import duke.Ui;
import task.TaskList;

/**
 * Represents a command returned by a Parser.
 */
public abstract class Command {
    protected boolean isExit = false;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns a Boolean that indicates whether this Command is an ExitCommand.
     *
     * @return Whether this is an ExitCommand.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
