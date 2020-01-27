package seedu.duke.Command;

import seedu.duke.Storage.Storage;
import seedu.duke.TaskList.TaskList;
import seedu.duke.Ui.Ui;

import java.io.IOException;

public abstract class Command {

    protected boolean isExit = false;

    public Command() {}

    public abstract void execute(TaskList t, Storage storage) throws IOException;

    public abstract String getResponse();

    public abstract boolean isExit();
}
