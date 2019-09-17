package duke.command;

import duke.Saved;
import duke.TaskList;
import duke.exception.DukeException;
import duke.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Saved file) throws DukeException;

    public boolean isBye() {
        return this instanceof ExitCommand;
    }
}
