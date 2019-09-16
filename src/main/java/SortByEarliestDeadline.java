import java.util.Comparator;

public class SortByEarliestDeadline implements Comparator<Task> {
    public int compare(Task a, Task b) {
        if (a.getTaskType().equals("[T]") && b.getTaskType().equals("[T]")) {
            return 0;
        } else if (b.getTaskType().equals("[T]")) {
            return 1;
        } else if (a.getTaskType().equals("[T]")) {
            return -1;
        } else {
            return a.getDateTimeAsDateTime().compareTo(b.getDateTimeAsDateTime());
        }
    }
}
