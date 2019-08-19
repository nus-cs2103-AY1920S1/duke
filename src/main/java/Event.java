public class Event extends Task {

    String duration;

    Event(String taskName, boolean isDone, String duration) {
        super(taskName, isDone);
        this.duration = duration;
    }

    @Override
    public String toString(){
        String mark = isDone ? "✓" : "✗";
        return "[E][" + mark + "]" + taskName +
                " (at:" + duration + ")";
    }
}
