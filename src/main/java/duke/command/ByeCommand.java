package duke.command;


import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    public boolean isExit(){
        return true;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage){

    }
}
