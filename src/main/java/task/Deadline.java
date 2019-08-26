package task;

public class Deadline extends Task {
    Date date;

    /**
     * Constructor for Deadline objects.
     * @param description String description of the deadline task.
     * @param date String of the deadline date. (Example: "18/08/2019").
     */
    public Deadline(String description, String date) {
        super(description.trim());
        this.date = new Date(date);
    }

    /**
     * toString() returns the String that is represented by this task.
     * @return String.
     */
    @Override
    public String toString() {
        String displayDate = "by: " + this.date.toString();
        return "[D][" + super.getStatusIcon() + "] "
                + super.description + " (" + displayDate + ")";
    }

    /**
     * toDataFormat() returns the String that is used to write the Storage file.
     * @return String.
     */
    public String toDataFormat() {
        return "D | " + super.getStatusIcon() + " | " + super.description + " | " + this.date.origin();
    }
}
