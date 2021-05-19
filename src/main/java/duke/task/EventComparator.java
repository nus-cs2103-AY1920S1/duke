package duke.task;

import duke.calendar.Date;
import duke.calendar.Time;

import java.util.Comparator;

public class EventComparator extends TaskComparator {

    /**
     * Compares events according to chronological order.
     * @param eventOne First event to be compared.
     * @param eventTwo Second event to be compared.
     * @return Returns a negative integer, zero, or a positive integer as the first event is less than,
     *     equal to, or greater than the second.
     */
    public int compare(Event eventOne, Event eventTwo) {
        if (eventOne.equals(eventTwo)) {
            return 0;
        } else {
            return compareChronologicalOrder(eventOne, eventTwo);
        }
    }

    private int compareChronologicalOrder(Event eventOne, Event eventTwo) {
        if (compareStart(eventOne, eventTwo) == 0) {
            return compareEnd(eventOne, eventTwo);
        } else {
            return compareStart(eventOne, eventTwo);
        }
    }

    private int compareStart(Event eventOne, Event eventTwo) {
        Date startDateOne = eventOne.getStartDate();
        Date startDateTwo = eventTwo.getStartDate();
        if (startDateOne.compareTo(startDateTwo) == 0) {
            return compareStartTime(eventOne, eventTwo);
        } else {
            return startDateOne.compareTo(startDateTwo);
        }
    }

    private int compareStartTime(Event eventOne, Event eventTwo) {
        Time startTimeOne = eventOne.getStartTime();
        Time startTimeTwo = eventTwo.getStartTime();
        if (startTimeOne == null) {
            return 1;
        } else {
            return startTimeOne.compareTo(startTimeTwo);
        }
    }

    private int compareEnd(Event eventOne, Event eventTwo) {
        Date endDateOne = eventOne.getEndDate();
        Date endDateTwo = eventTwo.getEndDate();
        if (endDateOne == null) {
            return 1;
        } else if (endDateOne.compareTo(endDateTwo) == 0) {
            return compareEndTime(eventOne, eventTwo);
        } else {
            return endDateOne.compareTo(endDateTwo);
        }
    }

    private int compareEndTime(Event eventOne, Event eventTwo) {
        Time endTimeOne = eventOne.getEndTime();
        Time endTimeTwo = eventTwo.getEndTime();
        if (endTimeOne == null) {
            return 1;
        } else {
            return endTimeOne.compareTo(endTimeTwo);
        }
    }

}
