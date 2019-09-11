import java.util.ArrayList;

public class Statistics {
    TaskList tasks;
    int numOfEvents = 0;

    /**
     * Constructor.
     * The statistics class takes in a TaskList
     * and retrieves statistics of the Tasklist
     * @param tasks
     */
    public Statistics (TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Generates statistics for this tasklist.
     */
    public void generateStatistics() {
        for (Task t : this.tasks.getWholeList()) {
           if (t instanceof Event) {
               this.numOfEvents++;
           }
        }
    }

    @Override
    public String toString() {
        return "You have " + this.numOfEvents + " number of events";
    }
}
