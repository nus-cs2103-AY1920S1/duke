package duke.command;

import duke.handler.Storage;
import duke.handler.Ui;
import duke.task.TaskList;

/**
 * Command is the parent class of all commands, storing full command in its
 * state and having an execute method as well as an exit method.
 */
public abstract class Command {

    protected String fullCommand;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}
