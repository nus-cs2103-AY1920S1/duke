package command;

import main.Storage;
import main.TaskList;
import main.Ui;

import java.io.IOException;

/**
 * Abstract class for command.
 */
public abstract class Command {

    public abstract String execute(TaskList task, Ui ui, Storage storage) throws IOException;
}
