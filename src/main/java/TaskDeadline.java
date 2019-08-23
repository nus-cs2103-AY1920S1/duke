public class TaskDeadline extends Task {
    private String by;

    /**
     * Private constructor.
     * Constructs an TaskDeadline with the specified description and time.
     * 
     * @param description of the task
     * @param by the time at which the event is due
     */
    private TaskDeadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }


    /**
     * Factory method. Use this to construct this object. Returns a
     * TaskDeadline object with the specified description and time.
     * 
     * @param description of the task
     * @param by the time at which the event is due
     * @return a new TaskDeadline object.
     */
    public static TaskDeadline of(String description, String by) {
        try {
            String dateTime = DateTime.of(by).toString();
            return new TaskDeadline(description, dateTime);
        } catch (IllegalDateException e) {
            // 'by' processed as simple String
            return new TaskDeadline(description, by);
        }
    }

    /**
     * Factory method. Use this to construct this object.
     * Returns a TaskDeadline object from its string form.
     * 
     * @param this object's string form
     * @return a new TaskDeadline object
     */
    public static TaskDeadline ofFormattedForm(String formattedForm)
            throws DukeException {
        if (!formattedForm.startsWith("D")) {
            throw new DukeException("Given string is not in the correct format");
        } else {
            String description = formattedForm.split("\\s+")[1];
            String by = formattedForm.substring(
                    formattedForm.indexOf("by:") + 4,
                    formattedForm.length() - 1);
            return TaskDeadline.of(description, by);
        }
    }

    /**
     * Returns the string representation of this Task, for writing to file.
     * 
     * @return the string representation of this Task suitable for writing to file
     */
    public String toFileFormattedString() {
        return String.format("%s | %s | %s | %s)",
                type, getStatusIcon().equals("✓") ? "Y" : "N" , description, by);
    } 

    /**
     * Returns the string representation of this Task.
     * 
     * @return the string representation of this Task
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                type, getStatusIcon(), description, by);
    }
}
