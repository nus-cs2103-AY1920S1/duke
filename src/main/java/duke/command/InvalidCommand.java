package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.InvalidCommandException;

public class InvalidCommand extends Command {

    public InvalidCommand() { }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws InvalidCommandException {
        throw new InvalidCommandException("\t I'm sorry, but I don't know what that means :-(");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
