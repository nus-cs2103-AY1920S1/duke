package duke.date;

import java.time.LocalDateTime;

class Interval {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    Interval(LocalDateTime startTime, LocalDateTime endTime) {
        setStartTime(startTime);
        setEndTime(endTime);
    }

    void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    LocalDateTime getStartTime() {
        return startTime;
    }

    LocalDateTime getEndTime() {
        return endTime;
    }

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
