package duke.init;

/**
 * Implements a deadline task.
 * @author lyskevin
 */
public class Deadline extends Task {

    private String date;

    /**
     * Constructs a deadline task with the specified description.
     * @param description The specified description.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns the string representation of this deadline task.
     * @return The string representation of this deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), date);
    }

}
