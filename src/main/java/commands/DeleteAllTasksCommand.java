package commands;

import components.Storage;
import components.TaskList;

public class DeleteAllTasksCommand implements Command {
    @Override
    public String[] execute(Storage storage, TaskList taskList) throws DukeException {
        String[] response = taskList.deleteAll();
        storage.save(taskList.getArr());
        return response;
    }
}
