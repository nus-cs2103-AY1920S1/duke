package duke.task.tasks.entities;

import util.DateTime;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Class to encapsulate start and end time of tasks.
 */
public class TimeFrame implements Serializable, Comparable<TimeFrame> {
    private static final long serialVersionUID = 6529685098267111111L;

    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for TimeFrame.
     * @param start start of task
     * @param end end of task
     */
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

    /**
     * Returns description of time frame as pretty display output.
     * @return description of time frame
     */
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

    @Override
    public int compareTo(TimeFrame timeFrame) {
        if (this.end != null && timeFrame.end != null) {
            return this.end.compareTo(timeFrame.end);
        } else if (this.end != null) {
            return -1;
        } else if (timeFrame.end != null) {
            return 1;
        } else if (this.start != null && timeFrame.start != null) {
            return this.start.compareTo(timeFrame.start);
        } else {
            return 0;
        }
    }
}
