package duke.command;

import duke.exception.DukeShutDownException;

/**
 * Class representing a command that initiates shut down of the Duke
 * system.
 */
public class ByeCommand extends Command {

    public static final String KEYWORD = "bye";

    /**
     * Throws a DukeShutDownException, which should be handled by the
     * main Duke program as it sees fit.
     *
     * @return result feedback of the command to be printed to the user
     */
    public String execute() throws DukeShutDownException {
        throw new DukeShutDownException(ui.GOODBYE);
    }
}
