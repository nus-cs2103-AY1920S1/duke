package duke.task.tasks.entities;

import util.DateTime;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TimeFrame implements Serializable {
    private static final long serialVersionUID = 6529685098267111111L;

    private LocalDateTime start;
    private LocalDateTime end;

    public TimeFrame(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getDescription() {
        if (start == null && end == null) {
            // task has no timeframe
            return "";
        } else if (start == null) {
            // task done by a particular time
            return String.format("by: %s", DateTime.getString(end));
        } else if (end == null) {
            //task done after a particular time
            return String.format("after: %s", DateTime.getString(start));
        } else if (start.equals(end)) {
            //  task done at a particular time
            return String.format("at: %s", DateTime.getString(start));
        } else {
            // task is done between two times
            return String.format("from: %s to: %s", DateTime.getString(start), DateTime.getString(end));
        }
    }
}
