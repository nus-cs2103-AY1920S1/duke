package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.DukeFileWriteException;
import duke.storage.Storage;

/**
 * Abstraction of command that requires a generic save to disk operation after execution.
 */
abstract class WritableCommand extends Command {
    WritableCommand(String[] args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeInvalidArgumentException, DukeFileWriteException {
        super.execute(tasks, ui, storage);
        storage.saveTasksToDisk(tasks);
    }
}
