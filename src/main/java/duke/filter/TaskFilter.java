package duke.filter;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * An abstract class representing a filter to filter tasks by task property.
 */
public abstract class TaskFilter implements Filter, Predicate<Task> {
    /**
     * Returns an ArrayList of tasks that are filtered out by the filter.
     * @param tasks a list of tasks to be filtered.
     * @return an ArrayList of tasks that are filtered out by the filter.
     */
    @Override
    public ArrayList<Task> filter(TaskList tasks) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task: tasks.getTasks()) {
            if (test(task)) {
                result.add(task);
            }
        }
        return result;
    }
}