package seedu.duke.comparator;

import seedu.duke.task.Task;
import seedu.duke.task.Todo;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import java.util.Comparator;
import java.util.Date;

public class DateComparator<T extends Task> implements Comparator<T> {

    String order;

    /**
     * Creates a date comparator for sorting purpose.
     * @param order Chooses whether to sort in ascending/descending order.
     */
    public DateComparator(String order) {
        this.order = order;
    }

    /**
     * Compares two tasks by date and returns integer depending on order specified.
     * Todo tasks always have the lowest priority.
     * @param t1 First task to compare.
     * @param t2 Second task to compare.
     * @return -1, 0, or 1 as first task is less than, equal to, or greater than the second task.
     *         Reverse if the order is descending.
     */
    public int compare(Task t1, Task t2) {
        if (t1 instanceof Todo) {
            if (t2 instanceof Todo) {
                return 0;
            } else {
                return 1;
            }
        } else if (t2 instanceof Todo) {
            return -1;
        } else {
            if (order.equals("ascending")) {
                return t1.getDate().compareTo(t2.getDate());
            } else if (order.equals("descending")) {
                return t2.getDate().compareTo(t1.getDate());
            } else {
                return 0;
            }
        }
    }
}
