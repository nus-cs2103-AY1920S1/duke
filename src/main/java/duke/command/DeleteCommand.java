package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    private int itemNum;

    public DeleteCommand(int itemNum) {
        super();
        this.itemNum = itemNum;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert taskList != null : "tasklist cannot be null";
        assert storage != null : "storage cannot be null";

        try {
            Task removedTask = taskList.deleteFromTaskList(itemNum - 1);
            return String.join("\n", Messages.DELETE_TASK_MESSAGE,
                    Messages.COMMAND_INDENTATION + Messages.COMPLETION_INDENTATION + removedTask.toString(),
                    String.format(Messages.LIST_SIZE_FORMAT, taskList.getSize()));
        } catch (IndexOutOfBoundsException e) {
            return Messages.INVALID_SIZE_EXCEPTION;
        }
    }
}
