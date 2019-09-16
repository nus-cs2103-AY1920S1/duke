package duke.filter;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class StatusFilter extends TaskFilter {
    private boolean isDoneStatus;

    /**
     * Constructor specifying the isDoneStatus for the filter.
     * @param isDoneStatus isDoneStatus for the filter.
     */
    public StatusFilter(boolean isDoneStatus) {
        this.isDoneStatus = isDoneStatus;
    }

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

    /**
     * Returns true if the task passes the filter.
     * @param task the task to be tested
     * @return true if the task passes the filter.
     */
    @Override
    public boolean test(Task task) {
        return task.getStatus() == isDoneStatus;
    }
}
