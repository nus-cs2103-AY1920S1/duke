package seedu.duke;

/**
 * Handles exiting the application by setting isExit as true.
 */
public class ExitCommand extends Command {

    /** Constructor.*/
    public ExitCommand() {
        super();
        isExit = true;
        type = "exit";
    }

    /**
     * Prints the exit greeting.
     * @param t TaskList.
     * @param u Ui.
     * @param s Storage.
     * @return a String containing the exit message.
     */
    @Override
    public ChatDisplay execute(TaskList t, Ui u, Storage s) {
        return u.exitLine();
    }
}
