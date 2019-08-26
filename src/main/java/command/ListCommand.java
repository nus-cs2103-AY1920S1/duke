package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a Command which prompts a printing of the TaskList.
 */
public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(tasks.toString());
    }

    public boolean isExit() {
        return false;
    }
}
