package command;

import util.Storage;
import util.TodoList;

public abstract class Command {
    public String run(TodoList tasks, Storage storage) {
        storage.save(tasks);
        return null;
    }

    public abstract boolean isExit();
}
