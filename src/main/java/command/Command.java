package command;

import java.util.Optional;

/***
 * Command interface to create classes in charge of executing specific actions in the program.
 */
public interface Command {
    /***
     * Runs execution code for a specific action.
     * @return following command if available.
     */
    public Optional<Command> execute();
}
