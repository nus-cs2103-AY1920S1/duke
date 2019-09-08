package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;


public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();

}
