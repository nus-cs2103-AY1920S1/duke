package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command {
    public DeleteCommand(String[] description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.length < 2) {
            throw new DukeException("☹ OOPS!!! The index of a delete task cannot be empty.");
        }
        int totalNumber = tasks.numberOfTasks();
        int index = Integer.parseInt(description[1]);

        if (index > totalNumber || index <= 0) {
            throw new DukeException("☹ OOPS!!! The task is not found.");
        }

        Task deletedTask = tasks.removeTask(index);
        storage.save(tasks);

        ui.showTask(deletedTask, tasks, "     Noted. I've removed this task: ");
    }
}
