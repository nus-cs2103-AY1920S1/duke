package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    @Override
    protected void check(TaskList tasks) throws DukeException {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        check(tasks);
        if (tasks.size() == 0) {
            ui.showMessage("There are currently no tasks in your list");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            ui.showMessage(tasks.toString());
        }
    }
}
