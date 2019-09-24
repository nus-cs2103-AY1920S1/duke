package duke.task;

import java.util.Comparator;
import java.util.Date;

public class TaskComparator implements Comparator<Task> {
    public int compare(Task obj1, Task obj2) {
        if (obj1 instanceof Deadline && obj2 instanceof Todo) {
            return -1;
        } else if (obj1 instanceof Todo && obj2 instanceof Deadline) {
            return 1;
        } else if (obj1 instanceof Todo && obj2 instanceof Event) {
            return 1;
        } else if (obj1 instanceof Event && obj2 instanceof Todo) {
            return -1;
        } else if ( obj1 instanceof Event && obj2 instanceof Deadline) {
            return 1;
        } else if (obj1 instanceof Deadline && obj2 instanceof Event) {
            return -1;
        } else {
            if (obj1 instanceof Deadline && obj2 instanceof Deadline) {
                Date d1 = ((Deadline)obj1).getDate();
                Date d2 = ((Deadline)obj2).getDate();
                if (d1.compareTo(d2) > 0) {
                    return 1;
                } else if (d1.compareTo(d2) < 0) {
                    return -1;
                } else {
                    return 0;
                }
            } else if (obj1 instanceof Event && obj2 instanceof Event) {
                Date d1 = ((Event) obj1).getDate();
                Date d2 = ((Event) obj2).getDate();
                if (d1.compareTo(d2) > 0) {
                    return 1;
                } else if (d1.compareTo(d2) < 0) {
                    return -1;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        }
    }
}
