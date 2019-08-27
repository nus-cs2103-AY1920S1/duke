package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;

public class ListCommand extends Command {

    public ListCommand(){
        this.commandType = CommandType.LIST;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printListCommand(taskList);
    }
}
