package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    public DeleteCommand(int n) {
        super.n = n;
    }

    public void execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        try {
            Task deletedTask = t.list.remove(n);
            ui.showDeletedTask(deletedTask, t.list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please input a number that is within the list");
        }
    }
}
