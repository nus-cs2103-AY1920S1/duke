package duke.command;

import duke.calendar.Date;
import duke.calendar.Time;
import duke.task.Deadline;

import java.util.Comparator;

public class DeadlineComparator implements Comparator<Deadline> {

    public int compare(Deadline deadlineOne, Deadline deadlineTwo) {
        if (deadlineOne.equals(deadlineTwo)) {
            return 0;
        } else {
            return compareChronologicalOrder(deadlineOne, deadlineTwo);
        }
    }

    private int compareChronologicalOrder(Deadline deadlineOne, Deadline deadlineTwo) {
        Date dateOne = deadlineOne.getDate();
        Date dateTwo = deadlineTwo.getDate();
        if (dateOne.compareTo(dateTwo) == 0) {
            return compareTime(deadlineOne, deadlineTwo);
        } else {
            return dateOne.compareTo(dateTwo);
        }
    }

    private int compareTime(Deadline deadlineOne, Deadline deadlineTwo) {
        Time timeOne = deadlineOne.getTime();
        Time timeTwo = deadlineTwo.getTime();
        if (timeOne == null) {
            return 1;
        } else {
            return timeOne.compareTo(timeTwo);
        }
    }

}
