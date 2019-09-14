package duke.command;

import duke.task.TaskList;
import duke.exception.DukeException;
import duke.io.Ui;
import duke.io.Storage;

/**
 * The Command class provides an abstract implementation of a user input command that executes
 * a certain set of instructions based on the command.
 */
public abstract class Command {
    protected String command;
    public static final String EXIT = "bye";

    public Command(String command) {
        this.command = command;
    }

    public boolean isExit() {
        return this.command.equals(EXIT);
    }

    /**
     * Executes the command defined by this object and a String representing the action.
     *
     * @param t the TaskList object
     * @param ui the Ui object
     * @param storage the Storage object
     * @return a String representing the action.
     * @throws DukeException if the command is unable to execute correctly
     */
    public abstract String execute(TaskList t, Ui ui, Storage storage)
            throws DukeException;

    @Override
    public String toString() {
        return this.command;
    }
}
