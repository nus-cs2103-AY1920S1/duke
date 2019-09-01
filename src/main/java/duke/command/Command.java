package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.text.ParseException;

public class Command {

    protected boolean isExit;

    public Command() {
        isExit = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParseException {

    }

    public boolean isExit() {
        return isExit;
    }
}