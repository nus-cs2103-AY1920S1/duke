package duke.Commands;

import duke.DirectProcessor.TaskList;
import duke.DirectProcessor.Ui;
import duke.DukeException;

public abstract class Command {

    protected String taskName;
    protected String taskTime;

    public abstract void execute(TaskList tl, Ui ui) throws DukeException;

    public abstract boolean isExit();
}
