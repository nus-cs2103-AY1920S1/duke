package duke.command;

import duke.handler.Storage;
import duke.handler.Ui;
import duke.task.TaskList;

public abstract class Command {

    protected String fullCommand;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}
