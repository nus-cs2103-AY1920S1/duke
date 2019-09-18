package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.handler.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Command {
    public String response;

    public abstract void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException, IOException;
}
