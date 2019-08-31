package command;

import exception.DukeException;
import filewriter.Storage;
import task.TaskList;
import ui.Ui;

public abstract class Command {
    FullCommand type;

    public boolean isExit(){
        return type.getName().equals("bye");
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
