package seedu.duke;

import java.io.IOException;

/**
 * Handles marking tasks in the TaskList as done.
 */
public class DoneCommand extends Command {
    /** An integer representing the position of the task in the TaskList to mark as done.*/
    protected static int done;

    /** Constructor.*/
    public DoneCommand(int d) {
        done = d;
        type = "done";
    }

    /**
     * Marks task in the corresponding position in the TaskList as done.
     * @param t TaskList.
     * @param u Ui.
     * @param s Storage.
     * @return a String containing the Done message.
     */
    @Override
    public ChatDisplay execute(TaskList t, Ui u, Storage s) {
        Task task = t.list.get(done);
        task.done();
        try {
            s.update(false, "", t);
        } catch (IOException e) {
            u.showError(e.getMessage());
        } finally {
            return u.doneLine(task.toString());
        }
    }
}
