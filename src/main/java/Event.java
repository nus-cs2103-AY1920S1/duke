public class Event extends Task {

    String time;

    public Event(int num, String task, String time, String type) {
        super(num, task, type);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", task, time);
    }

    @Override
    public String addTask() {
        return "    Got it. I've added this task:\n       [E][✗] ";
    }
}
