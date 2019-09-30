package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an abstract command.
 */
public abstract class Command {
    public abstract String exec(Storage storage, TaskList tasks, Ui ui);
}
