package Commands;

import DirectProcessor.Storage;
import DirectProcessor.TaskList;
import DirectProcessor.Ui;
import Tasks.Task;

import java.util.ArrayList;

public abstract class Command {

    protected String taskName;
    protected String taskTime;

    public abstract void execute(TaskList tl, Ui ui);

    public abstract boolean isExit();
}
