package duke.commands;

import java.io.IOException;

import duke.core.TaskList;
import duke.core.Ui;

import duke.errors.DukeException;
import duke.errors.DukeExceptionType;

public class NullCommand extends Command{

    public NullCommand(){
        this.commandType = CommandType.NULL;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException, IOException {
        throw new DukeException("Invalid commands.Command! Please try again.", DukeExceptionType.INVALIDCOMMAND);
    }

}
