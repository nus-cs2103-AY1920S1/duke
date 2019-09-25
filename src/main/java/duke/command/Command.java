package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public abstract class Command {

    public abstract String execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isTerminated();

}
