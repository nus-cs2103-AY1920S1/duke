package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.TaskDoesNotExistException;

public class DeleteTaskCommand extends Command {
    public DeleteTaskCommand(String commandInformation) {
        super(commandInformation);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws TaskDoesNotExistException {
        int taskNumber = Integer.parseInt(commandInformation);
        tasks.deleteTask(taskNumber, true);
        storage.writeToTasksFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
