package duke.command;

import duke.exception.DukeShutDownException;

/**
 * Class representing a command that initiates shut down of the Duke
 * system.
 */
public class ByeCommand extends Command {

    /**
     * Throws a DukeShutDownException, which should be handled by the
     * main Duke program as it sees fit.
     */
    public void execute() throws DukeShutDownException {
        throw new DukeShutDownException("Shutting down...");
    }
}
