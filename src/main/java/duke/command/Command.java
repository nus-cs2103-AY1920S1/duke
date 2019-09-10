package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    private String commandContent;
    protected boolean isExit = false;

    public Command() {
    }

    public Command(String commandContent) {
        this.commandContent = commandContent;
    }

    /**
     * Getter for variable isExit.
     * @return Whether program should exit.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Execute the command.
     *
     * @param tasks List of tasks in memory
     * @param ui Ui instance
     * @param storage Storage instance
     * @throws DukeException If command word is invalid
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
