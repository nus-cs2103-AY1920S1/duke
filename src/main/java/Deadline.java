/*
 * Deadline.java
 * Level-4
 * CS2103T
 * @author Gabriel Ong
 *
 * This class represents a Deadline Task.
 *
 */

public class Deadline extends Task {
    protected String by;

    public Deadline(String description) {
        super(description);
        String[] splitDescription = description.split(" /by ", 2);
        this.description = splitDescription[0];
        this.by = splitDescription[1];
    }

    @Override
    public String toString() {
        return "[D]"+ super.toString() + " (by: " + by + ")";
    }
}
