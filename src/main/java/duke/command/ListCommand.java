package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.task.TaskList;

/**
 * Represents a list command, execution lists all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showLine();
        ui.out("Here are the tasks in your list:");
        ui.list(taskList);
        ui.showLine();
    }
}
