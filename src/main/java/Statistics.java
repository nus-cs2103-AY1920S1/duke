import java.util.ArrayList;

public class Statistics {
    TaskList tasks;
    int numOfToDos = 0;
    int numOfCompletedToDos = 0;
    int numOfUncompletedToDos = 0;
    int numOfEvents = 0;
    int numOfCompletedEvents = 0;
    int numOfUncompletedEvents = 0;
    int numOfDeadlines = 0;
    int numOfCompletedDeadlines = 0;
    int numOfUncompletedDeadlines = 0;

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
               if (t.isMarkedAsDone()) {
                   this.numOfCompletedEvents++;
               } else {
                   this.numOfUncompletedEvents++;
               }
           } else if (t instanceof Deadline) {
               this.numOfDeadlines++;
               if (t.isMarkedAsDone()) {
                   this.numOfCompletedDeadlines++;
               } else {
                   this.numOfUncompletedDeadlines++;
               }
           } else if (t instanceof ToDo) {
               this.numOfToDos++;
               if (t.isMarkedAsDone()) {
                   this.numOfCompletedToDos++;
               } else {
                   this.numOfUncompletedToDos++;
               }
           }
        }
    }

    @Override
    public String toString() {
        return "     ---------- S T A T I S T I C S ----------\n" +
                String.format("%-15s %15s %15s", "Type : ", "Completed : ", "Uncompleted :\n") +
                String.format("%s %17d %16d", "ToDo      ",
                        this.numOfCompletedToDos, this.numOfUncompletedToDos) + "\n" +
                String.format("%s %16d %16d", "Event      ",
                        this.numOfCompletedEvents, this.numOfUncompletedEvents) + "\n" +
                String.format("%s %17d %16d", "Deadline ",
                        this.numOfCompletedDeadlines, this.numOfUncompletedDeadlines);
    }
}
