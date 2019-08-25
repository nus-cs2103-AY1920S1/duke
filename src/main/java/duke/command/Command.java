package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui);

    public abstract boolean isExit();

}
