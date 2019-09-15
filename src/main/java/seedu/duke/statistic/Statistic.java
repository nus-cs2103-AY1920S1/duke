package seedu.duke.statistic;

import seedu.duke.task.Task;
import seedu.duke.tasklist.TaskList;

import java.time.LocalDateTime;
import java.util.TreeMap;

/**
 * This class serves to store statistics of the user.
 */
public class Statistic {
    protected int totalCommandsExecuted;
    protected int totalTasksDeleted;
    protected int totalTodosCompleted;
    protected int totalDeadlinesCompleted;
    protected int totalEventsCompleted;

    /**
     * Default constructor. Reads a TreeMap, obtained from Storage.
     *
     * @param map Treemap mapping String to Integer.
     */
    public Statistic(TreeMap<String, Integer> map) {

        totalCommandsExecuted = map.get("totalCommandsExecuted");
        totalTasksDeleted = map.get("totalTasksDeleted");
        totalTodosCompleted = map.get("totalTodosCompleted");
        totalDeadlinesCompleted = map.get("totalDeadlinesCompleted");
        totalEventsCompleted = map.get("totalEventsCompleted");
    }

    /**
     * Resets all the attributes to 0.
     */
    public void resetStats() {
        totalCommandsExecuted = 0;
        totalTasksDeleted = 0;
        totalTodosCompleted = 0;
        totalDeadlinesCompleted = 0;
        totalEventsCompleted = 0;
    }

    /**
     * Returns the no. of events completed between now and one day ago.
     *
     * @param tasks TaskList object.
     * @return Int no. of events completed between now and one day ago.
     */
    public int getCompletedEventsFromOneDayAgo(TaskList tasks) {
        return getSpecifiedCompletedTasksFromVariableDaysAgo(tasks, 'E', 1);
    }

    /**
     * Returns the no. of events completed between now and any number of days ago.
     *
     * @param tasks TaskList object.
     * @param taskType Character representing task type.
     * @param daysAgo Int number of days ago.
     * @return Number of tasks completed within the specified time frame.
     */
    public int getSpecifiedCompletedTasksFromVariableDaysAgo(TaskList tasks, char taskType, int daysAgo) {
        int count = 0;
        for (Task t : tasks.getArrayList()) {
            if ((t.getTaskType() == taskType) && (t.isDone())) {
                LocalDateTime taskModifiedDateTime = t.getLastModifiedDateTime();
                LocalDateTime currentDateTime = LocalDateTime.now();

                // If lastModifiedDate is greater than yesterday, increment count
                if (taskModifiedDateTime.compareTo(currentDateTime.minusDays(daysAgo)) > 0) {
                    count += 1;
                }

            }
        }
        return count;
    }

    /**
     * Returns the no. of deaadlines completed between now and one day ago.
     *
     * @param tasks TaskList object.
     * @return Int no. of events completed between now and one day ago.
     */
    public int getCompletedDeadlinesFromOneDayAgo(TaskList tasks) {
        return getSpecifiedCompletedTasksFromVariableDaysAgo(tasks, 'D', 1);
    }

    /**
     * Increments total Commands Executed.
     */
    public void incrementTotalCommandsExecuted() {
        totalCommandsExecuted += 1;
    }

    /**
     * Increments Total Tasks Deleted.
     */
    public void incrementTotalTasksDeleted() {
        totalTasksDeleted += 1;
    }

    /**
     * Increments Total todos Completed.
     */
    public void incrementTotalTodosCompleted() {
        totalTodosCompleted += 1;
    }

    /**
     * Increments total Deadlines Completed.
     */
    public void incrementTotalDeadlinesCompleted() {
        totalDeadlinesCompleted += 1;
    }

    /**
     * Increments Total Events Completed.
     */
    public void incrementTotalEventsCompleted() {
        totalEventsCompleted += 1;
    }

    /**
     * Getter function for total commands executed.
     *
     * @return Total commands executed.
     */
    public int getTotalCommandsExecuted() {
        return totalCommandsExecuted;
    }

    /**
     * Getter function for total tasks deleted.
     *
     * @return Total tasks deleted.
     */
    public int getTotalTasksDeleted() {
        return totalTasksDeleted;
    }

    /**
     * Getter function for total todos completed.
     *
     * @return Total todos completed.
     */
    public int getTotalTodosCompleted() {
        return totalTodosCompleted;
    }

    /**
     * Getter function for total deadlines completed.
     *
     * @return Total deadlines completed.
     */
    public int getTotalDeadlinesCompleted() {
        return totalDeadlinesCompleted;
    }

    /**
     * Getter function for total events completed.
     *
     * @return Total events completed.
     */
    public int getTotalEventsCompleted() {
        return totalEventsCompleted;
    }

}
