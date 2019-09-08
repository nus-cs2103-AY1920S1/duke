package duke.command;

import duke.exception.DukeException;
import duke.history.History;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public abstract class Command {
    public abstract String execute(TaskList tasks, Storage storage, History history) throws DukeException;
}
