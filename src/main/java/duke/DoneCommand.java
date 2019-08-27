package duke;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private int idx;

    public DoneCommand(int idx, boolean isExit) {
        super(isExit);
        this.idx = idx;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        // mark the task corresponding to index as done
        Task task = taskList.markAsDone(idx);

        // inform the user task has been marked done
        ui.showDoneMessage(task);

        // update hard disk
        storage.writeToDisk(taskList);
    }
}
