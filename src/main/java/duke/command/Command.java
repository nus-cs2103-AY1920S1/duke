package duke.command;

import duke.Ui;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit(){
        return false;
    };
}

