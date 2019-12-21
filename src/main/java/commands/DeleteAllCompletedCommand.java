package commands;

import components.Storage;
import components.TaskList;

public class DeleteAllCompletedCommand implements Command {
    @Override
    public String[] execute(Storage storage, TaskList taskList) throws DukeException {
        String[] response = taskList.removeCompletedTasks();
        storage.save(taskList.getArr());
        return response;
    }
}
