package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.task.TaskList;

/**
 * Represents an exit command, execution prints goodbye message.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showLine();
        ui.out("Bye. Hope to see you again soon!");
        ui.showLine();
    }
}
