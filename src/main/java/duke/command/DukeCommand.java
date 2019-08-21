package duke.command;

import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import duke.util.DukeUi;

public abstract class DukeCommand {
    public abstract void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage);
}
