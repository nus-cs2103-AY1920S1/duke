package command;

import exception.DukeException;
import filewriter.Storage;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    public ListCommand(){
        super.type = FullCommand.LIST;
    }
    public boolean isExit(){
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.readList(tasks);
    };
}
