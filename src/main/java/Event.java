public class Event extends Task {

    String duration;

    Event(String taskName, boolean isDone, String duration) {
        super(taskName, isDone);
        this.duration = duration;
    }

    @Override
    public String toFile(){
        String mark = isDone ? "1" : "0";
        return "E | " + mark + " |" + taskName + "|" + duration;
    }

    @Override
    public String toString(){
        String mark = isDone ? "✓" : "✗";
        return "[E][" + mark + "]" + taskName +
                "(at:" + duration + ")";
    }
}
