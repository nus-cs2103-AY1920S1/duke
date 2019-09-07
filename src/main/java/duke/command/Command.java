package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui) throws DukeException;

    public abstract boolean isExit();
}
