package seedu.duke.task;

public class Todo extends Task {

    /**
     * Constructs a new Todo object with 'done' status set to false.
     * @param task Description of the task.
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Constructs a new Todo object with the 'done' status passed in as a parameter.
     * Used when loading data from saved file.
     * @param task Description of the task.
     * @param done Status of the task.
     */
    public Todo(String task, String done) {
        super(task);
        if (done.equals("1")) {
            super.markAsDone();
        }
    }

    /**
     * Converts this Todo object into storage string form.
     * @return Data of this object, in storage string form.
     */
    public String toStorageString() {
        String output = "T|";
        if (super.isDone) {
            output = output + "1|";
        } else {
            output = output + "0|";
        }
        output = output + super.taskName;
        return output;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
