/**
 * Todos are tasks without any date/time attached to it.
 */
public class Todo extends Task {

    /**
     * Todos can be initialised from the parent class Task constructor.
     *
     * @param description Activity that the user should complete.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Formats the todo to be stored in the hard disk.
     *
     * @return Todo details in the format T | 1 or 0 | description.
     */
    @Override
    public String toSave() {
        return "T | " + super.getBinaryStatus() + " | " + super.description;
    }

    /**
     * Formats the todo to be displayed to the user.
     *
     * @return Todo details in the format [T][v or x] description.
     */
    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }

}