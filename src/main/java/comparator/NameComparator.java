package seedu.duke.comparator;

import seedu.duke.task.Task;
import java.util.Comparator;

public class NameComparator<T extends Task> implements Comparator<T> {

    String order;

    public NameComparator(String order) {
        this.order = order;
    }

    public int compare(Task t1, Task t2) {
        if (order.equals("ascending")) {
            return t1.getTaskName().compareTo(t2.getTaskName());
        } else if (order.equals("descending")) {
            return t2.getTaskName().compareTo(t1.getTaskName());
        } else{
            return 0;
        }

    }
}
