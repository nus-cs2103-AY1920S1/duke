/**
 * This child class of Task contains the same attributes as its parent class but with an additional field (the deadline
 * of that task, which is stored as 'by').
 */
package duke.tasks;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String format() {
        String formatted = "D | ";
        int binary = 0;
        if (super.isDone == true) {
            binary = 1;
        }
        formatted += binary + " | " + super.description + " | " + this.by;
        return formatted;
    }
}
