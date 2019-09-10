package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;

public class DoneCommand extends Command {
    public DoneCommand(String[] description) {
        super(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.length < 2) {
            throw new DukeException("☹ OOPS!!! The index of a done task cannot be empty.");
        }
        int totalNumber = tasks.numberOfTasks();
        int index = Integer.parseInt(description[1]);

        if (index > totalNumber || index <= 0) {
            throw new DukeException("☹ OOPS!!! The task is not found.");
        }

        Task checkedTask = tasks.doneTask(index);
        storage.save(tasks);

        return ui.showTask(checkedTask, tasks, "Nice! I've marked this task as done: ");
    }
}
