package duke.util;

public class ListManager {
    private TaskList tasks;
    private NoteList notes;

    public ListManager() {
        this.tasks = new TaskList();
        this.notes = new NoteList();
    }

    public ListManager(TaskList tasks, NoteList notes) {
        this.tasks = tasks;
        this.notes = notes;
    }

    public String getBothListsAsString() {
        return tasks.getListAsString() + "\n" + notes.getListAsString();
    }

    public NoteList getNotes() {
        return notes;
    }

    public TaskList getTasks() {
        return tasks;
    }
}
