/*
 * Deadline.java
 * Level-6
 * CS2103T
 * @author Gabriel Ong
 *
 * This class represents a Deadline Task.
 *
 */

public class Deadline extends Task {
    protected String by;

    public Deadline(String description) throws DukeException {
        super(description);
        String[] splitDescription = description.split(" /by ", 2);

        try {
            this.description = splitDescription[0];
            this.by = splitDescription[1];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a deadline using \"/by\".");
        }
    }

    @Override
    public String toString() {
        return "[D]"+ super.toString() + " (by: " + by + ")";
    }
}
