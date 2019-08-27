package duke.command;

import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx, boolean isExit) {
        super(isExit);
        this.idx = idx;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        // delete the task at corresponding index
        Task task = taskList.delete(idx);

        // inform user of deletion
        ui.showDeleteMessage(task, taskList.count());

        // update hard disk
        storage.writeToDisk(taskList);
    }
}
