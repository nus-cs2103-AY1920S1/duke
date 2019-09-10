package duke.filter;

import duke.task.Task;
import duke.task.TaskType;

/**
 * A class representing a filter to filter tasks by type.
 */
public class TypeFilter extends TaskFilter {
    private TaskType taskType;

    /**
     * Constructor specifying the task type.
     * @param taskType the task type to be compared to.
     */
    public TypeFilter(TaskType taskType) {
        this.taskType = taskType;
    }

    /**
     * Returns true if the the task passes the filter.
     * @param task a task to be tested.
     * @return true if the the task passes the filter.
     */
    @Override
    public boolean test(Task task) {
        return task.getTaskType().equals(taskType);
    }
}
