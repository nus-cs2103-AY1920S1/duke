package command;

import java.util.Optional;

/***
 * <p>
 * Command interface to create classes in charge of executing specific actions in the program.
 * </p>
 */
public interface Command {
    /***
     * <p>
     * Runs execution code for a specific action.
     * </p>
     * @return following command if available.
     */
    public Optional<Command> execute();
}
