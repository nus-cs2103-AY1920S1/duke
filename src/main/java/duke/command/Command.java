package duke.command;

import duke.*;

public abstract class Command {
    public abstract void execute(Duke duke, TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
