package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.ui.Ui;

public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui) throws DukeException;

    public boolean isBye() {
        return this instanceof ExitCommand;
    }
}
