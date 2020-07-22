package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    /**
     * Executes a command using the given task list, UI and file storage.
     *
     * @param tasks the task list supplied.
     * @param ui the UI supplied.
     * @param storage the file storage supplied.
     * @throws DukeException if the command fails to execute.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Determines if a command is an exit command.
     *
     * @return whether the command is an exit command.
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
