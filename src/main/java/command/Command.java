package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

import java.io.IOException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;

    public abstract boolean isExit();

}
