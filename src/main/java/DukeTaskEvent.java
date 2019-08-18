public class DukeTaskEvent extends DukeTask {
    private String taskLocation;

    public DukeTaskEvent(String taskName, String taskLocation) {
        super(taskName);
        this.taskLocation = taskLocation;
    }

    public String getTaskLocation() {
        return this.taskLocation;
    }

    @Override
    public String toString() {
        String symbol = getTaskIsComplete() ? "\u2713" : "\u2718";
        return "[E][" + symbol + "] " + getTaskName() + " (by: " + this.taskLocation + ")";
    }
}
