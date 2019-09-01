package command;

import duke.Storage;
import duke.Ui;
import task.TaskList;

public class EmptyCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // returns an empty string
        return "";
    }
}
