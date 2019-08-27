package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DoneCommand extends Command {
    protected final int index;

    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        Task task = taskList.get(index);
        task.markAsDone();
        storage.update(taskList);
        ui.showDoneMessage(task);
    }
}
