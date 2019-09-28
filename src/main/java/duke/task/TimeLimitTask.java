package duke.task;

public abstract class TimeLimitTask extends Task {
    private String dateTime;

    /**
     * Constructs an task with time limit.
     *
     * @param description the description of the time limit task.
     * @param d the specific time limit of that task.
     */
    public TimeLimitTask(String description, String d) {
        super(description);
        this.dateTime = d;
    }

    /**
     * Informs the time limit of a particular task.
     *
     * @return the time limit of that task.
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Updates the deadline of a particular deadline task.
     *
     * @param newDate New deadline of that task.
     */
    public void updateTime(String newDate) {
        this.dateTime = newDate;
    }
}
