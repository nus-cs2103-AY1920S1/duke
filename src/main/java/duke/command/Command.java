package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public abstract void execute(Duke duke, TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
