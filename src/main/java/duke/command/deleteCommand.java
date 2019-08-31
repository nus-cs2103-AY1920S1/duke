package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class deleteCommand implements Command{
    private int deleteIndex;
    public deleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task removedTask = tasks.deleteTask(deleteIndex);
        ArrayList<Task> list = tasks.getList();
        ui.showDelete(removedTask, list.size());
        storage.saveFile(list);
    }
}
