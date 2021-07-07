package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.PastOperationList;
import duke.task.TaskList;

public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, PastOperationList pastOperationList) {
        assert taskList != null : "tasklist cannot be null";
        assert storage != null : "storage cannot be null";

        String formattedString = taskList.getTasksInString(taskList.getTaskList());
        String message = String.join("\n", Messages.LIST_MESSAGE,
                Messages.COMMAND_INDENTATION + formattedString);
        return message;
    }
}
