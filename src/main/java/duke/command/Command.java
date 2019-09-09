package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;
}
