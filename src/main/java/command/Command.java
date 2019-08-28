package main.java.command;

import main.java.TaskList;
import main.java.Ui;
import main.java.Storage;
import main.java.exception.DukeException;

public abstract class Command {
    protected boolean isExit = false;

    public boolean isExit() {
        return isExit;
    };

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}