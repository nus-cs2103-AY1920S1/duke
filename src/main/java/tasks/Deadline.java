package tasks;

import tasks.Task;

public class Deadline extends Task {
    protected String by;
    protected String year;
    protected String month;
    protected String day;
    protected String time;

    /**
     * constructor of Deadline task.
     *
     * @param description description of the task
     * @param by          time
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        String[] splits = by.split("[/ ]");
        day = splits[0];
        month = splits[1];
        year = splits[2];
        time = splits[3];
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String writer() {
        String text = "D | ";
        if (getStatus() == false) {
            text = text.concat("0 | " + getDescription() + " | " + getBy());
        } else {
            text = text.concat("1 | " + getDescription() + " | " + getBy());
        }
        return text;
    }
}
