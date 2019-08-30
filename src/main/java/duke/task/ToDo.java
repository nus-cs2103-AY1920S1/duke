package duke.task;

/**
 * Represents a ToDo with a name.
 */
public class ToDo extends Task {
    ToDo(String name, boolean done) {
        super(name, done);
    }

    public ToDo(String name) {
        this(name, false);
    }

    /**
     * Returns a string formatted for storage in the storage file.
     *
     * @return String
     */
    @Override
    public String storageString() {
        return "T," + (super.getDone() ? "1," : "0,") + super.getName();
    }

    /**
     * Returns a string for normal printing to represent state and details of the Task.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Checks for logical equality of this instance to another object.
     *
     * @param obj Another object in question.
     * @return true if logically equivalent, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof ToDo) {
            ToDo other = (ToDo) obj;
            return this.getDone() == other.getDone() && this.getName().equals(other.getName());
        }
        return false;
    }
}
