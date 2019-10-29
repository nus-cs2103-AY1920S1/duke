package duke.command;

import duke.core.DukeResponder;
import duke.util.Storage;
import duke.util.TaskList;

public abstract class Command {

    public abstract String execute(TaskList tasks, DukeResponder responder, Storage storage);

}
