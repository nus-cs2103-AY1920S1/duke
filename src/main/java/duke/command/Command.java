package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage);

}
