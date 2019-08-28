public class Event extends Task {

    private String time;

    public Event(String taskName, boolean done, String time) {
        super(taskName, done);
        this.time = time;
    }

    public String toString() {
        if (done) {
            return "[E][✓]" + taskName + "(at:" + time + ")";
        } else {
            return "[E][✗]" + taskName + "(at:" + time + ")";
        }
    }

    public String storageFormat() {
        if (done) {
            return "E/✓/" + taskName + "/" + time;
        } else {
            return "E/✗/" + taskName + "/" + time;
        }
    }
}
