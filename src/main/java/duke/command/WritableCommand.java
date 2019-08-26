package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.DukeFileWriteException;
import duke.storage.Storage;

/**
 * Abstraction of command that requires a generic save to disk operation after execution.
 */
abstract class WritableCommand extends Command {
    /**
     * Generic constructor of the writable command from its arguments.
     *
     * @param commandArgs String array of arguments
     */
    WritableCommand(String[] commandArgs) {
        super(commandArgs);
    }

    /**
     * Validates and runs the command, then saves the tasks to disk.
     * It is an override implementation of Command's execute,
     * with the additional step of saving to disk.
     *
     * @param tasks TaskList of tasks to use.
     * @param ui Ui to use for displaying command output.
     * @param storage Storage for WritableCommands to execute write-to-disk operations.
     * @throws DukeInvalidArgumentException If an argument is invalid for the command.
     * @throws DukeFileWriteException If a file write operation of a command fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeInvalidArgumentException, DukeFileWriteException {
        super.execute(tasks, ui, storage);
        storage.saveTasksToDisk(tasks);
    }
}
