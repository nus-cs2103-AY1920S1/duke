package seedu.duke.statistic;

import org.joda.time.DateTime;
import org.joda.time.Days;
import seedu.duke.task.Task;
import seedu.duke.tasklist.TaskList;

import java.time.LocalDateTime;
import java.util.TreeMap;

/**
 * This class serves to store statistics on the user.
 * This class reads a txt file, called "stats.txt" during initialization.
 */
public class Statistic {
    protected int totalCommandsExecuted;
    protected int totalTasksDeleted;
    protected int totalTodosCompleted;
    protected int totalDeadlinesCompleted;
    protected int totalEventsCompleted;

    /**
     * Default constructor.
     */
    public Statistic (TreeMap<String, Integer> map) {
         totalCommandsExecuted = map.get("totalCommandsExecuted");
         totalTasksDeleted = map.get("totalTasksDeleted");
         totalTodosCompleted = map.get("totalTodosCompleted");
         totalDeadlinesCompleted = map.get("totalDeadlinesCompleted");
         totalEventsCompleted = map.get("totalEventsCompleted");
    }

    public void resetStats(){
        totalCommandsExecuted = 0;
        totalTasksDeleted = 0;
        totalTodosCompleted = 0;
        totalDeadlinesCompleted = 0;
        totalEventsCompleted = 0;
    }

    public int getCompletedEventsFromOneDayAgo(TaskList tasks){
        return getSpecifiedCompletedTasksFromVariableDaysAgo(tasks, 'E', 1);
    }

    public int getSpecifiedCompletedTasksFromVariableDaysAgo(TaskList tasks, char taskType, int daysAgo){
        int count = 0;
        for (Task t : tasks.getArrayList()){
            if ( (t.getTaskType() == taskType) && (t.isDone())){
                LocalDateTime taskModifiedDateTime = t.getLastModifiedDateTime();
                LocalDateTime currentDateTime = LocalDateTime.now();

                // If lastModifiedDate is greater than yesterday, increment count
                if (taskModifiedDateTime.compareTo(currentDateTime.minusDays(daysAgo)) > 0){
                    count += 1;
                }

            }
        }
        return count;
    }

    public void incrementTotalCommandsExecuted() {
        totalCommandsExecuted += 1;
    }

    public void incrementTotalTasksDeleted() {
        totalTasksDeleted += 1;
    }

    public void incrementTotalTodosCompleted() {
        totalTodosCompleted += 1;
    }

    public void incrementTotalDeadlinesCompleted() {
        totalDeadlinesCompleted += 1;
    }

    public void incrementTotalEventsCompleted() {
        totalEventsCompleted += 1;
    }

    public int getTotalCommandsExecuted() {
        return totalCommandsExecuted;
    }

    public int getTotalTasksDeleted() {
        return totalTasksDeleted;
    }

    public int getTotalTodosCompleted() {
        return totalTodosCompleted;
    }

    public int getTotalDeadlinesCompleted() {
        return totalDeadlinesCompleted;
    }

    public int getTotalEventsCompleted() {
        return totalEventsCompleted;
    }

}
