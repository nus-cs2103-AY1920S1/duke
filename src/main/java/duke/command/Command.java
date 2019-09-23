package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * The interface for Commands used in the Duke program.
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @param tasks The list of tasks the command should act on.
     * @param storage The Storage that the command should act on.
     * @return The output of the command as a String array.
     * @throws DukeException If command fails to execute to completion.
     */
    String[] execute(TaskList tasks, Storage storage) throws DukeException;

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