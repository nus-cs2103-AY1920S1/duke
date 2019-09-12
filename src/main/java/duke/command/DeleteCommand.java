package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;

import static duke.ui.Message.DELETE_MESSAGE;

public class DeleteCommand extends Command {
    public DeleteCommand(String[] description) {
        super(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert super.description != null;
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

        return ui.showTask(deletedTask, tasks, DELETE_MESSAGE);
    }
}
