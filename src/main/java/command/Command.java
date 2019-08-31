package command;

import main.Storage;
import main.TaskList;
import main.Ui;

import java.io.IOException;

public abstract class Command {

    public abstract void execute(TaskList task, Ui ui, Storage storage) throws IOException;
}
