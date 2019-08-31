package duke.command;

import duke.exception.DukeException;

import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

abstract public class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();

}
