package duke.commands;

import java.io.IOException;

import duke.core.TaskList;
import duke.core.Ui;

import duke.errors.DukeException;


public class ExitCommand extends Command {

    public ExitCommand(){
        this.commandType = CommandType.EXIT;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException, IOException {
        ui.printByeMessage();
    }

}

