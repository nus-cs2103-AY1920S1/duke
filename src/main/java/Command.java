/**
 * Simulates a possible command of the Duke System.
 * @author Fabian Chia Hup Peng
 */

public abstract class Command {

    public boolean isExit;

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns true if isExit is true, else false.
     * @return The boolean value of isExit.
     */
    public boolean isExit() {
        return isExit;
    }

}
