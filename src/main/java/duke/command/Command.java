package duke.command;

import duke.exception.DukeException;
/**
 * The base class for all commands in the chatbot application.
 */

public abstract class Command {
    protected String parameters;

    /**
     * Default public constructor for Command.
     */
    public Command() {

    }

    /**
     * Alternative public constructor for Command.
     * @param parameters Information needed to execute the command.
     */
    public Command(String parameters) {
        this.parameters = parameters;
    }

    public abstract String executeCommand() throws DukeException;
}
