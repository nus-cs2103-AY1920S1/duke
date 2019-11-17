package duke.extension;

import duke.exception.NullDateException;
import duke.task.Task;
import duke.task.Todo;

import java.util.Comparator;

/**
 * A default comparator for tasks to handle insertion sort.
 */
public class InsertionSortComparator implements Comparator<Task> {
    /**
     * Compares two different {@link Task}. Returns <code>1</code> if the <code>task1 &gt; task2</code>, <code>0</code>
     * if <code>task1 == task2</code> and <code>-1</code> if <code>task1 &lt; task2</code>.
     * @param task1 the first task to be compared
     * @param task2 the second task to be compared
     * @return <code>1</code> if the <code>task1 &gt; task2</code>, <code>0</code> if <code>task1 == task2</code> and
     * <code>-1</code> if <code>task1 &lt; task2</code>.
     */
    public int compare(Task task1, Task task2) {
        try {
            return task1.getTaskDate().compareTo(task2.getTaskDate());
        } catch (NullDateException nde) {
            assert (isTodo(task1) || isTodo(task2));
            if (isTodo(task1) && isTodo(task2)) {
                return 0;
            } else if (isTodo(task1)) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    /**
     * Checks whether the specified task is a {@link Todo} item.
     * @param task the task to be checked
     * @return <code>true</code> if the task is a todo and <code>false</code> otherwise.
     */
    private Boolean isTodo(Task task) {
        return task instanceof Todo;
    }

}
