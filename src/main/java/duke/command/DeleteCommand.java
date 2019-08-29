package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.*;

public class DeleteCommand extends Command {
    protected int deletedTask;

    public DeleteCommand(int deleted) {
        super();
        this.deletedTask = deleted;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printRemoveMessage(tasks.delete(this.deletedTask), tasks.size());
    }
}