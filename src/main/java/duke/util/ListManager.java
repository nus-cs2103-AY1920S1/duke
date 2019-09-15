package duke.util;

/**
 * ListManager class to manage both Tasklist and Notelist.
 */
public class ListManager {
    private TaskList tasks;
    private NoteList notes;

    /**
     * Constructor for a new ListManager.
     */
    public ListManager() {
        this.tasks = new TaskList();
        this.notes = new NoteList();
    }

    /**
     * Constructor for an existing ListManager when loading from history.
     *
     * @param tasks list of tasks
     * @param notes list of notes
     */
    public ListManager(TaskList tasks, NoteList notes) {
        this.tasks = tasks;
        this.notes = notes;
    }

    /**
     * Return the string representation of both lists.
     *
     * @return String representation of both lists
     */
    public String getBothListsAsString() {
        return tasks.getListAsString() + "\n" + notes.getListAsString();
    }

    /**
     * Returns the NoteList.
     *
     * @return NoteList
     */
    public NoteList getNotes() {
        return notes;
    }

    /**
     * Returns the TaskList.
     *
     * @return TaskList
     */
    public TaskList getTasks() {
        return tasks;
    }
}
