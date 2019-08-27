package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;

import slave.exception.DukeException;
import slave.exception.InvalidCommandException;

public class NullCommand extends Command {

    private String command;

    public NullCommand(String command) {
        this.commandType = CommandType.NULL;
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        throw new InvalidCommandException(command);
    }
}
