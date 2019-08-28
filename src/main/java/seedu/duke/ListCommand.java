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
     */
    @Override
    public void execute(TaskList t, Ui u, Storage s) {
        u.list(t);
    }
}
