package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class doneCommand implements Command{
    private int doneIndex;
    boolean isExist = false;

    public doneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task doneTask = tasks.doneTask(doneIndex);
        ui.showDone(doneTask);
        storage.saveFile(tasks.getList());
    }
}
