package seedu.duke.comparator;

import seedu.duke.task.Task;
import java.util.Comparator;

public class NameComparator<T extends Task> implements Comparator<T> {

    String order;

    /**
     * Creates a name comparator for sorting purpose.
     * @param order Chooses whether to sort in ascending/descending order.
     */
    public NameComparator(String order) {
        this.order = order;
    }

    /**
     * Compares two tasks by name and returns integer depending on order specified.
     * @param t1 First task to compare.
     * @param t2 Second task to compare.
     * @return -1, 0, or 1 as first task is less than, equal to, or greater than the second task.
     *         Reverse if the order is descending.
     */
    public int compare(Task t1, Task t2) {
        if (order.equals("ascending")) {
            return t1.getTaskName().compareTo(t2.getTaskName());
        } else if (order.equals("descending")) {
            return t2.getTaskName().compareTo(t1.getTaskName());
        } else {
            return 0;
        }

    }
}
