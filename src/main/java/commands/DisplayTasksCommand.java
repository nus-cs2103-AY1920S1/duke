package commands;

import components.Storage;
import components.TaskList;

public class DisplayTasksCommand implements Command {
    @Override
    public String[] execute(Storage storage, TaskList taskList) {
        return taskList.displayList();
    }
}
