package duke.extension;

import duke.exception.NullDateException;
import duke.task.Task;
import duke.task.Todo;

import java.util.Comparator;

public class InsertionSortComparator implements Comparator<Task> {
    public int compare(Task task1, Task task2) {
        try{
            return task1.getTaskDate().compareTo(task2.getTaskDate());
        } catch (NullDateException nde) {
            if(isTodo(task1) && isTodo(task2)) {
                return 0;
            } else if(isTodo(task1)) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    private Boolean isTodo(Task task) {
        return task instanceof Todo;
    }

}
