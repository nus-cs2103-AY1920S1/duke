package duke.filter;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * A class representing a filter to filter tasks by indices.
 */
public class IndexFilter implements Filter {
    private IntStream range;

    /**
     * Constructor specifying a range of indices of resulting tasks.
     * @param range a range of indices of resulting tasks.
     */
    public IndexFilter(IntStream range) {
        this.range = range.sorted().distinct();
    }

    /**
     * Returns an ArrayList of tasks that are filtered out by the filter.
     * @param tasks a list of tasks to be filtered.
     * @return an ArrayList of tasks that are filtered out by the filter.
     */
    @Override
    public ArrayList<Task> filter(TaskList tasks) {
        ArrayList<Task> result = new ArrayList<>();
        range.forEach(index -> {
            try {
                result.add(tasks.getTaskAtIndex(index));
            } catch (Exception e) {
                //Ignore out of bounds index
            }
        });
        return result;
    }
}