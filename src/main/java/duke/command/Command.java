package duke.command;

import duke.DukeException.DukeException;
import duke.task.Task;
import duke.taskHandler.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException, IOException;
}
