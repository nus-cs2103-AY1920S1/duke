package duke;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskFactory;
import duke.task.TaskList;
import duke.task.TaskType;

public class AddCommand extends Command {
    private TaskType type;
    private String[] args;

    protected AddCommand(TaskType type, String[] args, boolean isExit) {
        super(isExit);
        this.type = type;
        this.args = args;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        // create the appropriate task, add to the list and write to disk
        Task task = taskList.add(TaskFactory.getTask(type, args));

        // inform the user the task has been added
        ui.showAddMessage(task, taskList.count());

        // update hard disk
        storage.writeToDisk(taskList);
    }
}
