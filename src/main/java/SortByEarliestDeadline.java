import java.util.Comparator;

/**
 * Comparator for Task sorting earliest deadlines first, with todos ranked last.
 */
public class SortByEarliestDeadline implements Comparator<Task> {
    /**
     * Compares two tasks for sorting.
     * @param a First task for comparison
     * @param b Second task for comparison
     * @return -1 if a is smaller, 1 if b is smaller and 0 if both are equal.
     */
    @Override
    public int compare(Task a, Task b) {
        if (a.getTaskType().equals("[T]") & b.getTaskType().equals("[T]")) {
            return 0;
        } else if (b.getTaskType().equals("[T]")) {
            return -1;
        } else if (a.getTaskType().equals("[T]")) {
            return 1;
        } else {
            return a.getDateTimeAsDateTime().compareTo(b.getDateTimeAsDateTime());
        }
    }
}
