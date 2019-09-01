package command;

import exception.DukeException;
import filewriter.Storage;
import task.TaskList;
import ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand(){
        this.type = FullCommand.BYE;
    }

    public boolean isExit(){
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.closeWriter();
    };
}
