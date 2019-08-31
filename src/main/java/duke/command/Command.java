package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;

    public abstract boolean isExit();
}
