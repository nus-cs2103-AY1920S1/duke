package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class AddCommand extends Command {
    protected final Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        taskList.add(task);
        storage.update(taskList);
        ui.showAddMessage(taskList.size(), task);
    }
}
