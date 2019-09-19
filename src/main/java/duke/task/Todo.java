package duke.task;

public class Todo extends Task {

    /**
     * Constructor for the Todo object.
     *
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Prepares the task to be saved to flat file format.
     */
    @Override
    public String printSave() {
        return "T | " + ((isDone) ? "1" : "0") + " | " + description;
    }

    /**
     * Prepares the task to be output on the GUI.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}