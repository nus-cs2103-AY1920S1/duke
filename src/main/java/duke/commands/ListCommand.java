package duke.commands;

import java.io.IOException;


import java.util.List;

import duke.core.TaskList;
import duke.core.Ui;

import duke.errors.DukeException;

public class ListCommand extends Command {

    public ListCommand(){
        this.commandType = CommandType.LIST;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException, IOException {
        ui.printNumberList(taskList);
    }
}
