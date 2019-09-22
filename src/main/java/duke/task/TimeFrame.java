package duke.task;

import util.DateTime;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to encapsulate the time frame within which a task is to be completed. Not all tasks have a start and end
 * time so these two fields may be null. TimeFrames with no start signifies that the task is to be completed by a
 * certain time. TimeFrames with no end signifies that the task is to be done after a certain time. TimeFrames with
 * the same start and end time signifies that the task is to be done at a particular time. TimeFrames with no start
 * or end times signify that the task as no restrictions with regards to when it must be done.
 */
public class TimeFrame implements Serializable, Comparable<TimeFrame> {
    private static final long serialVersionUID = 6529685098267111111L;

    private final LocalDateTime start;
    private final LocalDateTime end;

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
     * A TimeFrame with no start and end times has no description. Thus, this method will return true
     * if the TimeFrame instance has no start and end time.
     * @return true if the TimeFrame has no description.
     */
    public boolean hasDescription() {
        return start == null && end == null;
    }

    /**
     * Returns a description of the TimeFrame as a nicely formatted String.
     * @return a nicely formatted description of the TimeFrame.
     */
    public String getDescription() {
        if (start == null && end == null) {
            // task has no timeframe
            return null;
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

    /**
     * Returns a the start and end LocalDateTimes of the TimeFrame instance as a list. The LocalDateTimes are added in
     * the order of start followed by end if they exist.
     * @return a list of the LocalDateTimes of the TimeFrame.
     */
    public List<LocalDateTime> getDateTimes() {
        List<LocalDateTime> result = new ArrayList<>();

        if (start != null) {
            result.add(start);
        }

        if (end != null) {
            result.add(end);
        }

        return result;
    }

    /**
     * Compares two TimeFrames based on which is later. -1 is returned if the TimeFrame being compared is later,
     * 1 is returned if the TimeFrame compared is earlier and 0 is returned if they are the same. TimeFrames are first
     * compared by their end time followed by their start time.
     * @param timeFrame the TimeFrame instance to compare to.
     * @return numerical value signifying which TimeFrame is later
     */
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

    /**
     * Compares an object to a TimeFrame instance to see if they are equal. Two time frames are equal if they have the same
     * start and end LocalDateTimes.
     * @param obj object to be compared.
     * @return true if the objects are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TimeFrame) {
            return ((TimeFrame) obj).getStart().equals(this.start) &&
                    ((TimeFrame) obj).getEnd().equals(this.end);
        }

        return false;
    }
}
