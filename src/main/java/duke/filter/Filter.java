package duke.filter;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Represents an operation to filter a list of tasks.
 */
public interface Filter {
    /**
     * Returns an ArrayList of tasks that are filtered out by the filter.
     * @param tasks a list of tasks to be filtered.
     * @return an ArrayList of tasks that are filtered out by the filter.
     */
    ArrayList<Task> filter(TaskList tasks);
}