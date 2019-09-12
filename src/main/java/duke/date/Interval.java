package duke.date;

import java.time.LocalDateTime;

/**
 * Represents a time interval with a demarcated start and end time.
 */
class Interval {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs a time interval.
     * @param startTime Starting time of the interval.
     * @param endTime Ending time of the interval.
     */
    Interval(LocalDateTime startTime, LocalDateTime endTime) {
        setStartTime(startTime);
        setEndTime(endTime);
    }

    /**
     * Sets the start time of the interval.
     * @param startTime The starting time.
     */
    void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets the end time of the interval.
     * @param endTime The ending time.
     */
    void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets the starting time of the interval.
     * @return Etarting time.
     */
    LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Gets the ending time of the interval.
     * @return Ending time.
     */
    LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Checks if the input interval overlaps with the current interval.
     * @param interval Interval to check for overlap.
     * @return True if there is overlap, false otherwise.
     */
    boolean isOverlapping(Interval interval) {
        return !isNonOverlapping(interval);
    }

    private boolean isNonOverlapping(Interval interval) {
        boolean isBefore = isBeforeInterval(interval);
        boolean isAfter = isAfterInterval(interval);
        return isBefore || isAfter;
    }

    private boolean isBeforeInterval(Interval interval) {
        boolean isCurrentEndBeforeEndTime = this.endTime.isBefore(interval.getEndTime());
        boolean isCurrentEndBeforeStartTime = this.endTime.isBefore(interval.getStartTime());
        boolean isCurrentEndEqualToStartTime = this.endTime.isEqual(interval.getStartTime());
        return isCurrentEndBeforeEndTime
                && (isCurrentEndBeforeStartTime || isCurrentEndEqualToStartTime);
    }

    private boolean isAfterInterval(Interval interval) {
        boolean isCurrentStartAfterStartTime = this.startTime.isAfter((interval.getStartTime()));
        boolean isCurrentStartAfterEndTime = this.startTime.isAfter(interval.getEndTime());
        boolean isCurrentStartEqualToEndTime = this.startTime.isEqual(interval.getEndTime());
        return isCurrentStartAfterStartTime
                && (isCurrentStartAfterEndTime || isCurrentStartEqualToEndTime);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Interval) {
            Interval i = (Interval) obj;
            return i.endTime.isEqual(endTime) && i.startTime.isEqual(startTime);
        } else {
            return false;
        }
    }
}
