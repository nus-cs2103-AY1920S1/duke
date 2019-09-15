package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Return true if the command is a ExitCommand.
     * @return boolean true/false depending on whether command is ExitCommand object or not
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
