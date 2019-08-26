package duke.command;

import duke.ui.Ui;
import duke.task.TaskList;
import duke.storage.DukeFileWriteException;
import duke.storage.Storage;

/**
 * Abstraction of an executable command.
 * Validates the inputs provided then runs it.
 */
abstract public class Command {
    public Commands commandType;
    String[] commandArgs;

    Command(String[] commandArgs) {
        this.commandArgs = commandArgs;
    }

    abstract void run(TaskList tasks, Ui ui, Storage storage);
    abstract void validate(TaskList tasks, Ui ui, Storage storage)
            throws DukeInvalidArgumentException;

    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeInvalidArgumentException, DukeFileWriteException {
        validate(tasks, ui, storage);
        run(tasks, ui, storage);
    }
}