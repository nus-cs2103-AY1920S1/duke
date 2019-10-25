package task;

/**
 * A simple Task which only indicates what task needs to be completed.
 */
public class Todo extends Task {

    public Todo(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns a String representing the Task in a format to be saved into the hard disk's storage file.

     * @return A String representation of this Task formatted for the storage file.
     */
    public String toFile() {
        if (isDone) {
            return "T-1-" + name;
        } else {
            return "T-0-" + name;
        }
    }

    /**
     * Returns a String representing the Task in a format to be printed into the console.
     *
     * @return A String representation of this Task formatted for printing into the console.
     */
    public String toString() {
        if (isDone) {
            return "[T][\u2713] " + name;
        } else {
            return "[T][\u2717] " + name;
        }
    }
}
