package seedu.duke;

/**
 * Abstract base Command class for Command objects.
 */
public abstract class Command {
    /** Boolean representing if the user has decided to exit the application.*/
    protected static boolean isExit;
    /** String representing the type of command.*/
    protected static String type;
    protected static TaskList task;
    protected static Ui ui;
    protected static Storage storage;

    /** Constructor.*/
    public Command() {
        isExit = false;
    }

    /**
     * Abstract method for executing Commands.
     * @param t TaskList.
     * @param u Ui.
     * @param s Storage.
     * @return
     */
    public abstract ChatDisplay execute(TaskList t, Ui u, Storage s);

    /**
     * Returns whether Task is done.
     * @return boolean representing whether Task is done.
     */
    public static boolean isExit() {
        return isExit;
    }
}
