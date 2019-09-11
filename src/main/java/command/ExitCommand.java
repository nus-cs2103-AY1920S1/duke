package command;

import util.Storage;
import util.TodoList;

public class ExitCommand extends command.Command {
    private static final String FAREWELL = "Bye. Hope to see you again soon!";

    @Override
    public String run(TodoList tasks, Storage storage) {
        return FAREWELL;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
