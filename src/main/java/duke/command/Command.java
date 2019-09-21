package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;
}
