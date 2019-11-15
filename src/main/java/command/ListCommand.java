package command;

import util.Storage;
import util.TodoList;

public class ListCommand extends command.Command {

    @Override
    public String run(TodoList tasks, Storage storage) {
        return tasks.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
