package duke.date;

import duke.task.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Checks if events are scheduled at conflicting times.
 */
public class EventConflictChecker {

    private static final String DATE_CONFLICT = "Error! This event clashes with an existing event.";
    private static ArrayList<Interval> bookedIntervals = new ArrayList<>();

    /**
     * Adds an interval to be blocked so that future events can't clash.
     * @param event Event whose interval is to be added.
     * @throws InvalidDateDukeException If the event's time slot clashes.
     */
    public static void addInterval(Event event) throws InvalidDateDukeException {
        Interval interval = makeIntervalFromEvent(event);
        boolean isInvalidInterval = isConflicting(interval);
        if (isInvalidInterval) {
            throw new InvalidDateDukeException(DATE_CONFLICT);
        }
        bookedIntervals.add(interval);
    }

    private static boolean isConflicting(Interval newInterval) {
        for (Interval interval : bookedIntervals) {
            if (interval.isOverlapping(newInterval)) {
                return true;
            }
        }
        return false;
    }

    private static Interval makeIntervalFromEvent(Event event) {
        LocalDateTime startTime = event.getStartTime();
        LocalDateTime endTime = event.getEndTime();
        Interval newInterval = new Interval(startTime, endTime);
        return newInterval;
    }

    /**
     * Deletes the time-slot corresponding to the input event, effectively freeing it.
     * @param event Event whose time slot is to be freed.
     */
    public static void deleteInterval(Event event) {
        Interval interval = makeIntervalFromEvent(event);
        removeInterval(interval);
    }

    private static void removeInterval(Interval interval) {
        for (Interval i : bookedIntervals) {
            if (i.equals(interval)) {
                bookedIntervals.remove(i);
                break;
            }
        }
    }
}
