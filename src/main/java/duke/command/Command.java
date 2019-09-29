package duke.command;

import duke.ui.MainWindow;
import duke.task.TaskList;
import duke.storage.DukeFileWriteException;
import duke.storage.Storage;

/**
 * Abstraction of an executable command.
 * Validates the inputs provided then runs it.
 */
public abstract class Command {
    /** Commands enum type of the command. */
    Commands commandType;

    /** String array of arguments provided to the command. */
    String[] commandArgs;

    /**
     * Generic constructor of a command from its arguments.
     *
     * @param commandArgs String array of arguments.
     */
    Command(String[] commandArgs) {
        this.commandArgs = commandArgs;
        assert this.commandArgs != null : "Command with null arguments constructed";
    }

    /**
     * Runs the processing logic of the command.
     *
     * @param tasks TaskList of tasks to use.
     * @param ui MainWindow to use for displaying command output.
     * @param storage Storage for WritableCommands to execute write-to-disk operations.
     */
    abstract void run(TaskList tasks, MainWindow ui, Storage storage);

    /**
     * Runs the validating logic of the command.
     *
     * @param tasks TaskList of tasks to use.
     * @param ui MainWindow to use for displaying command output.
     * @param storage Storage for WritableCommands to execute write-to-disk operations.
     * @throws DukeInvalidArgumentException If an argument is invalid for the command.
     */
    abstract void validate(TaskList tasks, MainWindow ui, Storage storage)
            throws DukeInvalidArgumentException;

    /**
     * Executes the command.
     * Validates the inputs first, then runs the processing logic.
     *
     * @param tasks TaskList of tasks to use.
     * @param ui MainWindow to use for displaying command output.
     * @param storage Storage for WritableCommands to execute write-to-disk operations.
     * @throws DukeInvalidArgumentException If an argument is invalid for the command.
     * @throws DukeFileWriteException If a file write operation of a command fails.
     */
    public void execute(TaskList tasks, MainWindow ui, Storage storage)
            throws DukeInvalidArgumentException, DukeFileWriteException {
        validate(tasks, ui, storage);
        run(tasks, ui, storage);
    }
}