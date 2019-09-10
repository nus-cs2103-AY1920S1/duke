package duke.filter;

import duke.task.Task;

import java.time.LocalDateTime;

/**
 * A class representing a filter to filter tasks by time.
 */
public class TimeFilter extends TaskFilter {
    private ComparisonOperator operator;
    private LocalDateTime dateTime;

    /**
     * Constructor specifying the comparison operator and the date and time.
     * @param operator a comparison operator
     * @param dateTime the date and time to be compared to.
     */
    public TimeFilter(ComparisonOperator operator, LocalDateTime dateTime) {
        this.operator = operator;
        this.dateTime = dateTime;
    }

    /**
     * Returns true if the task passes the filter.
     * @param task the task to be tested
     * @return true if the task passes the filter.
     */
    @Override
    public boolean test(Task task) {
        if (task.getDateTime() == null) {
            return false;
        }
        switch (operator) {
        case LessThan:
            return task.getDateTime().isBefore(dateTime);
        case LessThanOrEqualTo:
            return task.getDateTime().isBefore(dateTime) || task.getDateTime().isEqual(dateTime);
        case EqualTo:
            return task.getDateTime().isEqual(dateTime);
        case GreaterThanOrEqualTo:
            return task.getDateTime().isAfter(dateTime) || task.getDateTime().isEqual(dateTime);
        case GreaterThan:
            return task.getDateTime().isAfter(dateTime);
        default:
            return false;
        }
    }
}
