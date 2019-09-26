package duke.execution.command;

import java.io.IOException;

import duke.exceptions.DukeException;

import duke.execution.CompleteList;
import duke.execution.Storage;
import duke.execution.Ui;

public class Command {

    protected String action;
    protected String variable;
    private static boolean canExit = false;

    /**
     * Constructor for Command classes.
     *
     * @param action Description of tasks.
     */
    public Command(String action) {
        this.action = action;
    }

    /**
     * Constructor for Command classes.
     *
     * @param action Description of tasks.
     * @param variable Variable that changes depending on the command.
     */
    public Command(String action, String variable) {
        this.action = action;
        this.variable = variable;
    }

    /**
     * Tells the loop whether it should exit it.
     *
     * @return Returns the boolean canExit.
     */
    public boolean shouldExit() {
        return canExit;
    }

    /**
     * Changes the boolean canExit to the opposite value.
     *
     * @return Returns the newly changed boolean.
     */
    public boolean exitSwitch() { return canExit = !canExit; }

    /**
     * Dummy return value as it is always overrided by child classes.
     *
     * @param list Not needed in this case.
     * @param ui Not needed in this case.
     * @param storage Not needed in this case.
     * @return Returns a dummy string.
     * @throws IOException If the named file exists but is a directory rather
     *                     than a regular file, does not exist but cannot be
     *                     created, or cannot be opened for any other reason.
     * @throws DukeException If there is a different input that is not accepted,
     *                       a error message will show up.
     */
    public String execute(CompleteList list, Ui ui, Storage storage) throws IOException, DukeException {
        return "";
    }
}
