package jermi.command;

import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.component.Ui;
import jermi.exception.JermiException;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws JermiException;

    public abstract boolean shouldExit();
}
