package Command;

import Utilities.Storage;
import Utilities.TaskList;
import Utilities.Ui;

public abstract class Command {
    protected String command;

    public Command(String command){
        this.command = command;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    public abstract boolean isExit();
}
