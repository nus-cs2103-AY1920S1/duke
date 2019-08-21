public class Deadline extends Task {

    String time;

    public Deadline(int num, String task, String time, String type) {
        super(num, task, type);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", task, time);
    }

    @Override
    public String addTask() {
        return String.format("     Got it. I've added this task:\n       [D][✗] ");
    }

}
