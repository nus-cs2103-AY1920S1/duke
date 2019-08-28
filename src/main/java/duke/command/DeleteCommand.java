package duke.command;

import duke.task.Task;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String taskId) {
        super(taskId);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.remove(args);
        ui.printDeleteTask(task);
    }
}
