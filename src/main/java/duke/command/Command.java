package duke.command;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract boolean executeCommand(TaskList taskList, Storage storage, Ui ui)
            throws DukeException, IOException;
}
