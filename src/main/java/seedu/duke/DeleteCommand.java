package seedu.duke;

/**
 * Handles deleting tasks from the TaskList.
 */
public class DeleteCommand extends Command {
    /** An integer representing the position of the task in the TaskList to delete.*/
    protected static int delete;

    /** Constructor. */
    public DeleteCommand(int d) {
        delete = d;
        type = "delete";
    }

    /**
     * Deletes the Task in the corresponding position in the TaskList.
     * @param t TaskList.
     * @param u Ui.
     * @param s Storage.
     */
    @Override
    public void execute(TaskList t, Ui u, Storage s) {
        Task task = t.list.get(delete);
        t.list.remove(delete);
        u.deleteLine(task.toString(), t.list.size());
    }
}
