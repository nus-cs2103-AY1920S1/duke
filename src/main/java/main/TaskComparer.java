package main;

import task.DateTask;
import task.Task;
import task.ToDos;

import java.util.Comparator;

/**
 * Comparator for comparing Tasks for sorting.
 */
public class TaskComparer implements Comparator<Task> {

    @Override
    public int compare(Task a, Task b) {
        if (a instanceof ToDos && b instanceof ToDos) {
            return b.getDescription().compareTo(a.getDescription());
        } else if (a instanceof ToDos) {
            return 1;
        } else if (b instanceof ToDos) {
            return -1;
        } else {
            return ((DateTask) a).getDate().compareTo(((DateTask) b).getDate());
        }
    }
}
