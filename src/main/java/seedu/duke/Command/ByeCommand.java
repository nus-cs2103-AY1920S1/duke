package seedu.duke.Command;

import seedu.duke.Storage.Storage;
import seedu.duke.TaskList.TaskList;
import seedu.duke.Ui.Ui;

import java.io.IOException;

public class ByeCommand extends Command {

    public ByeCommand() {}

    @Override
    public void execute(TaskList t, Storage storage) throws IOException {

    }

    @Override
    public String getResponse() {
        return "Bye! Hope to see you again soon!";
    }


    @Override
    public boolean isExit() {
        return isExit;
    }
}
