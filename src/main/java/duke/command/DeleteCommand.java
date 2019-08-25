package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int zeroBasedIndex;

    public DeleteCommand(int oneBasedIndex) {
        this.zeroBasedIndex = oneBasedIndex - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (zeroBasedIndex < 0 || zeroBasedIndex >= tasks.size()) {
            throw new DukeException("\u2639 OOPS!!! The index to remove cannot be less than 0 or "
                    + "greater than the length of the list.");
        }

        Task deletedTask = tasks.get(zeroBasedIndex);
        tasks.delete(zeroBasedIndex);
        ui.printDeleteSuccessMessage(deletedTask, tasks.size());
        storage.save(tasks.getAllTasks());
    }
}
