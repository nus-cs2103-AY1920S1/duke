package duke;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskFactory;
import duke.task.TaskList;
import duke.task.TaskType;

import static duke.task.TaskType.TODO;

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
