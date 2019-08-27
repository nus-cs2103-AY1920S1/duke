package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;
import slave.exception.DukeException;

public abstract class Command {
    CommandType commandType;

    public CommandType getCommandType() {
        return commandType;
    }

    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;

}
