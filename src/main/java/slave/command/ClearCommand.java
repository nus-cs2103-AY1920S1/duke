package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;

import slave.exception.DukeException;

public class ClearCommand extends Command {

    public ClearCommand() {
        this.commandType = CommandType.CLEAR;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.clearList();
        ui.printClearCommand();
    }
}
