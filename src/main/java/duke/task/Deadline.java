package duke.task;

import duke.datetime.Date;
import duke.datetime.Timing;

public class Deadline extends Task {
    private Date date;
    private Timing timing;

    public Deadline(String description, String date, String timing, int done) {
        super(description, done);
        this.date = new Date(date);
        this.timing = new Timing(timing);
        Task.totalTasks++;
    }

    public Date getDate() {
        return date;
    }

    public Timing getTiming() {
        return timing;
    }

    public Deadline(String description, String date, String timing) {
        super(description);
        this.date = new Date(date);
        this.timing = new Timing(timing);
        Task.totalTasks++;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + date.toString() + ", " + timing.toString() + ")";
    }
}
