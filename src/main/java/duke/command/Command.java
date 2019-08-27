package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    Command() {

    }

    public abstract void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException;

    public boolean isBye() {
        return false;
    }
}

