package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Lists the number of tasks available.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            ui.printLine("You have no tasks in your list.");
            return;
        }

        ui.printLine("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            String output = String.format("%d. %s", i + 1, tasks.getTask(i));
            ui.printLine(output);
        }
    }
}
