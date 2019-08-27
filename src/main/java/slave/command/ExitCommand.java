package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;

public class ExitCommand extends Command {

    public ExitCommand(){
        this.commandType = CommandType.EXIT;
    }

    @Override
    public void execute(TaskList taskList, Ui ui){
        ui.showByeMessage();
    }
}

