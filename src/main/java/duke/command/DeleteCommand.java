package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    protected final int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        Task task = taskList.remove(index);
        storage.update(taskList);
        ui.showDeleteMessage(taskList.size(), task);
    }
}
