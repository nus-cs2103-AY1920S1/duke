package duke.task;

public class Note extends Task {

    /**
     * Creates a new note object.
     * @param taskName A string representing the content of the note.
     */
    public Note(String taskName) {
        super(taskName);
    }

    /**
     * Returns a string describing the task.
     * @return A string describing the task.
     */
    @Override
    public String toString() {
        return "[N] " + taskName + "\n";
    }

    /**
     * Returns a string in the format to be saved to disk.
     * @return A string in the format to be saved to disk.
     */
    public String toSaveString() {
        return "N|" + taskName;
    }
}
