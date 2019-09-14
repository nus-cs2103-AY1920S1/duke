package duke.commands;

import java.io.IOException;
import duke.errors.DukeException;

import duke.core.TaskList;
import duke.core.Ui;

public abstract class Command {
    CommandType commandType;

    public CommandType getCommandType() {
        return commandType;
    }

    public abstract void execute(TaskList taskList, Ui ui) throws DukeException, IOException;

}



