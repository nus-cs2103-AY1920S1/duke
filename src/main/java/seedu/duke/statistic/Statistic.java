package seedu.duke.statistic;

import java.util.Map;
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
        /*
        for(Map.Entry m:map.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue());
        }
        */
         totalCommandsExecuted = map.get("totalCommandsExecuted") + 1;
         totalTasksDeleted = map.get("totalTasksDeleted") + 1;
         totalTodosCompleted = map.get("totalTodosCompleted") + 1;
         totalDeadlinesCompleted = map.get("totalDeadlinesCompleted") + 1;
         totalEventsCompleted = map.get("totalEventsCompleted") + 1;
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
