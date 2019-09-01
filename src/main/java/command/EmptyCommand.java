package command;

import duke.Storage;
import duke.Ui;
import task.TaskList;

public class EmptyCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // do nothing

        // returns an empty string
        // return "";
    }
}
