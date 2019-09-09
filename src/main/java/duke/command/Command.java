package duke.command;

import error.ui.UiException;

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
     * @return following duke.command if available.
     */
    public void execute() throws UiException;
}
