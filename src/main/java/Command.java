/**
 * Abstract class that Represents an executable command.
 */
public abstract class Command {

    public static boolean isExit = false;

    /**
     * Indicates whether the program should end.
     *
     * @return Whether the program should end.
     */
    public static boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command.
     *
     * @param tasks Task list to refer to if necessary.
     * @param ui User interface that assists with printing.
     * @param storage Storage to be updated with changes if any.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

}
