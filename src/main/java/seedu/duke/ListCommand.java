package seedu.duke;

/**
 * Handles calling for the Ui to list TaskList Tasks.
 */
public class ListCommand extends Command {

    /**
     * Calls for Ui to print the TaskList.
     * @param t TaskList.
     * @param u Ui.
     * @param s Storage.
     * @return a String containing the list message.
     */
    @Override
    public ChatDisplay execute(TaskList t, Ui u, Storage s) {
        return u.list(t);
    }
}
