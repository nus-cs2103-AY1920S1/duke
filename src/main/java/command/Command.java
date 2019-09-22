package command;

import error.DukeException;
import utils.Storage;
import utils.TaskList;
import utils.Ui;
import java.util.ArrayList;

/**
 * A Abstract class for Command.
 * */
public abstract class Command {
    protected String command;

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}