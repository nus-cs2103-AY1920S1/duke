package command;

import utilities.Storage;
import utilities.TaskList;
import utilities.Ui;

public abstract class Command {
    protected String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    public abstract String executeAsString(TaskList tasks, Ui ui, Storage storage) throws Exception;

    public abstract boolean isExit();
}
