package duke.task.tasks.entities;

import util.DateTime;

import java.time.LocalDateTime;

public class TimeFrame {
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
            //task done from a particular time
            return String.format("from: %s", DateTime.getString(start));
        } else if (start.equals(end)) {
            //  task done at a particular time
            return String.format("at: %s", DateTime.getString(start));
        } else {
            // task is done between two times
            return String.format("from: %s to: %s", DateTime.getString(start), DateTime.getString(end));
        }
    }
}
