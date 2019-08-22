public class Event extends Task {

    private String time;

    public Event(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    public String toString() {
        if (done) {
            return "[E][✓]" + taskName + "(at:" + time + ")";
        } else {
            return "[E][✗]" + taskName + "(at:" + time + ")";
        }
    }
}
