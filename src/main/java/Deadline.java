/**
 * Encapsulates a Task object of the type Deadline.
 * Represents a Deadline task that has specific due date/ due time.
 */
public class Deadline extends Task {
    protected String endDate;

    /**
     * Constructs a new Deadline task.
     *
     * @param description This is the short description of the task.
     * @param endDate This is specifies when the Deadline Task expires.
     *                It should include the due date or due time or both.
     *                The format should follow "by: Day Time".
     *                E.g. by: Sunday 5pm / by: 11/10/2019 3pm
     *                Alternatively, the user has
     *                the freedom to specify their own duration of
     *                the event such as "by no idea :-P"
     */
    public Deadline(String description, String endDate) {
        super(description);
        this.endDate = endDate;
        this.typeOfTask = "D";
    }

    @Override
    public String toString() {
        String[] prepositionSplit = endDate.split(" ", 2);
        String statusIcon = this.getStatusIcon();
        return ("[" + typeOfTask + "]" + "[" + statusIcon + "] "
                + description + "(" + prepositionSplit[0] + ": "
                + prepositionSplit[1] + ")");
    }
}
