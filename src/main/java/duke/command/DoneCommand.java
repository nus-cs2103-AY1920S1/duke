package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.PastOperationList;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.UndoInfo;

public class DoneCommand extends Command {
    private int itemNum;

    public DoneCommand(int itemNum) {
        super();
        this.itemNum = itemNum;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, PastOperationList pastOperationList) {
        assert taskList != null : "tasklist cannot be null";
        assert storage != null : "storage cannot be null";

        try {
            Task completedTask = taskList.getTask(itemNum - 1);
            completedTask.completeTask();
            pastOperationList.keepTrackOfLastOperation(completedTask, new UndoInfo("undone"));

            return String.join("\n", Messages.DONE_MESSAGE, Messages.COMMAND_INDENTATION
                    + Messages.COMPLETION_INDENTATION + taskList.getTask(itemNum - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            return Messages.INVALID_SIZE_EXCEPTION;
        }
    }
}
