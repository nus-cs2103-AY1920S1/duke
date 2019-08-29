package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The interface for Commands used in the Duke program.
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @param tasks The list of tasks the command should act on.
     * @param ui The UI that the command interacts with.
     * @param storage The Storage that the command should act on.
     * @throws DukeException If command fails to execute to completion.
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the input string is a valid command.
     *
     * @throws DukeException if the input string is an invalid command.
     */
    void validate() throws DukeException;

    /**
     * Checks if the command should cause the Duke program to exit.
     *
     * @return true if command should cause the Duke program to exit, otherwise returns false.
     */
    boolean isExit();
}