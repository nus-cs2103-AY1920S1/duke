package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;

public abstract class Command {
    String params;

    /**
     * Constructs a plain command.
     */
    public Command() {
    }

    /**
     * Constructs a command with parameters.
     *
     * @param params Additional parameters necessary to execute the command.
     */
    public Command(String params) {
        this.params = params.trim();
    }

    /**
     * Executes the command.
     * This method is abstract and needs to be implemented by child classes.
     *
     * @param tasks   Task list containing all tasks.
     * @param storage Storage instance.
     * @return String output from the command.
     * @throws DukeException If command execution fails.
     */
    public abstract String executeCommand(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Getter for params.
     *
     * @return Params.
     */
    public String getParams() {
        return this.params;
    }
}
