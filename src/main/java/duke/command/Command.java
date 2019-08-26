package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui);

    public abstract boolean isExit();

}
